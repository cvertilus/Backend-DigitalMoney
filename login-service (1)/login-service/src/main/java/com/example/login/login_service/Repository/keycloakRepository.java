package com.example.login.login_service.Repository;

import com.example.login.login_service.model.*;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class keycloakRepository implements userRepository {

    @Autowired
    private Keycloak keycloak;
    @Value("${app.keycloak.realm}")
    String realm;
    @Value("${app.keycloak.clientid}")
    String clientid;
    @Value("${app.keycloak.clientsecret}")
    String clientsecret;

    private final String keycloakToken = "http://localhost:8080/realms/backend-digital-money/protocol/openid-connect/token";
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private AccountFeignClient accountFeignClient;


    @Override
    public List<UserRepresentation> getUsers() {

        return keycloak.realm(realm).users().list();
    }

    @Override
    public ResponseEntity<Map<String, Object>> loginUser(String email, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        if(!ExisteUsername(email)) return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Usuario inexistente", "status", 404));

        String  nameUSer = findByUsername(email).get(0).getUsername();

        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "password");
        body.put("client_id", clientid);
        body.put("client_secret", clientsecret);
        body.put("username", email);
        body.put("password", password);

        StringBuilder requestData = new StringBuilder();
        body.forEach((key, value) -> requestData.append(key).append("=").append(value).append("&"));
        requestData.setLength(requestData.length() - 1); // Eliminar el último "&"

        HttpEntity<String> requestEntity = new HttpEntity<>(requestData.toString(), headers);
        // Realizar la solicitud y manejar errores
        try {
            // Realizar la solicitud HTTP al servidor de Keycloak
            ResponseEntity<Map> response = restTemplate.exchange(
                    keycloakToken, HttpMethod.POST, requestEntity, Map.class);

            // ✅ Si la autenticación es exitosa
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("accessToken", response.getBody().get("access_token"));
                responseBody.put("message", "Autenticación exitosa");
                return ResponseEntity.ok(responseBody);
            }

            return ResponseEntity.status(response.getStatusCode())
                    .body(Map.of("error", "Error en autenticación", "status", response.getStatusCode().value()));

        } catch (HttpClientErrorException e) {
            HttpStatus status = (HttpStatus) e.getStatusCode();


            if (status == HttpStatus.UNAUTHORIZED) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Contraseña incorrecta", "status", 400));
            }

            return ResponseEntity.status(status)
                    .body(Map.of("error", "Error HTTP inesperado", "status", status.value()));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error interno del servidor", "status", 500));
        }

    }

    @Override
    public List<UserP> findByUsername(String username) {
        List<UserRepresentation> userRepresentation = keycloak
                .realm(realm)
                .users()
                .search(username);

        return userRepresentation.stream().map(user -> fromRepresentation(user)).collect(Collectors.toList());
    }

    private UserP fromRepresentation(UserRepresentation userRepresentation) {
        return new UserP(userRepresentation.getId(),userRepresentation.getFirstName(),userRepresentation.getLastName(),userRepresentation.getEmail(),userRepresentation.getUsername());}

    public Boolean ExisteUsername(String name) {
        List<UserP> users = findByUsername(name);
        return users != null && !users.isEmpty();
    }

    public ResponseEntity<Map<String,Object>>  registerUser (CreateUser createUser){
        if (ExisteUsername(createUser.getEmail())) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error","Usuario Existente","status",404));

        CredentialRepresentation credential = newCredential(createUser.getPassword());
        // Crear representación del usuario
        UserRepresentation user = newUserRepresentation(createUser,credential);
        try {
            Response register = createuserkeycloak(user);
            if(register.getStatus() == 201){
                String location = register.getLocation().toString();
                String id = location.substring(location.lastIndexOf("/")+1);
                user.setId(id);
                ResponseEntity<Map<String, Object>> login =  loginUser(createUser.getEmail(),createUser.getPassword());
                if(login.getStatusCode() == HttpStatus.OK){
                    String accessToken = login.getBody().get("accessToken").toString();
                    Account accountRequest = createAccountRequest(user);
                    ResponseEntity<?> account = accountFeignClient.createAccount(accountRequest,"Bearer " + accessToken);

                    if(account.getStatusCode() == HttpStatus.OK){
                        CreateUserDto userDto = new CreateUserDto(createUser.getFirstname(),createUser.getLastname(),createUser.getDni(),createUser.getEmail(),createUser.getPhone(),accountRequest.getCvu(),accountRequest.getAlias(),accountRequest.getUserId());
                        return ResponseEntity.ok().body(Map.of("user",userDto,"acccess_token",accessToken));

                    }else return (ResponseEntity<Map<String, Object>>) account;
                }
            }

        }catch (HttpClientErrorException e){
            HttpStatus status = (HttpStatus) e.getStatusCode();
            if (status == HttpStatus.BAD_REQUEST) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Message","Error en crear el usuario","status",404));

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Error interno del servidor", "status", 500));
        }
        return null;
    }

    private CredentialRepresentation newCredential ( String password) {

        CredentialRepresentation credentialRepresentation =  new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(password);
        credentialRepresentation.setTemporary(false);
        return credentialRepresentation;
    }

    private UserRepresentation newUserRepresentation (CreateUser user,CredentialRepresentation credentialRepresentation){
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getEmail());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setFirstName(user.getFirstname());
        userRepresentation.setLastName(user.getLastname());
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

        // Agregar DNI como atributo personalizado
        Map<String, List<String>> attributes = new HashMap<>();
        attributes.put("dni", Collections.singletonList(user.getDni()));
        attributes.put("phone",Collections.singletonList(user.getPhone()));
        userRepresentation.setAttributes(attributes);

        userRepresentation.setEnabled(true);

        return userRepresentation;
    }

    private Response createuserkeycloak (UserRepresentation userRepresentation){
        return keycloak.realm(realm).users().create(userRepresentation);
    }

    private Account createAccountRequest(UserRepresentation user){
        CvuAliasGenerator cvuAliasGenerator = new CvuAliasGenerator();
        String alias = cvuAliasGenerator.getAlias();
        String cvu = cvuAliasGenerator.getCvu();
        String name = user.getFirstName() + " " + user.getLastName();
        Account account = new  Account();
        account.setName(name);
        account.setCvu(cvu);
        account.setBalance(0);
        account.setAlias(alias);
        account.setUserId(user.getId());
        return  account;
    }

}




