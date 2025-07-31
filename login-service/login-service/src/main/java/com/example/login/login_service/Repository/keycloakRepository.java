package com.example.login.login_service.Repository;

import com.example.login.login_service.model.*;
import feign.FeignException;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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


    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private AccountFeignClient accountFeignClient;

    @Autowired
    private KeycloakFeign keycloakFeign;


    @Override
    public List<UserRepresentation> getUsers() {

        return keycloak.realm(realm).users().list();
    }

    @Override
    public ResponseEntity<Map<String, Object>> loginUser(String email, String password) {
        if (!ExisteUsername(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Usuario inexistente", "status", 404));
        }

        // Preparar el formulario
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", clientid);
        formData.add("client_secret", clientsecret);
        formData.add("username", email);
        formData.add("password", password);

        try {
            Map<String, Object> response = keycloakFeign.getToken(formData);

            if (response.containsKey("access_token")) {
                Map<String, Object> body = new HashMap<>();
                body.put("access_token", response.get("access_token"));
                body.put("message", "Autenticación exitosa");
                return ResponseEntity.ok(body);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Respuesta sin token", "status", 400));
            }

        } catch (FeignException.Unauthorized e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Contraseña incorrecta", "status", 400));


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
                    String accessToken = login.getBody().get("access_token").toString();
                    AccountRequest accountRequest = createAccountRequest(user);
                   return crearAccount(accountRequest, accessToken, createUser, id);
                }
            }

        }catch (HttpClientErrorException e){
            deleteUSer(user.getId());
            HttpStatus status = (HttpStatus) e.getStatusCode();
            if (status == HttpStatus.BAD_REQUEST) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Message","Error en crear el usuario","status",404));

        }catch (Exception e){
            deleteUSer(user.getId());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(e.getMessage(), "Error interno del servidor", "status", 500));
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

    private AccountRequest createAccountRequest(UserRepresentation user){
        String name = user.getFirstName() + " " + user.getLastName();
        AccountRequest accountRequest = new AccountRequest(name,user.getId());
        return  accountRequest;
    }

    private void deleteUSer(String userId) {
        keycloak.realm(realm).users().get(userId).logout();
            keycloak.realm(realm).users().get(userId).remove();

    }

    private ResponseEntity<Map<String, Object>> crearAccount(AccountRequest accountRequest, String accessToken, CreateUser createUser, String id) {
        
        try{

            ResponseEntity<Account> account = accountFeignClient.createAccount(accountRequest,"Bearer " + accessToken);
            Account accountResponse = account.getBody();
            CreateUserDto userDto = new CreateUserDto(createUser.getFirstname(),createUser.getLastname(),createUser.getDni(),createUser.getEmail(),createUser.getPhone(),accountResponse.getCvu(),accountResponse.getAlias(),accountRequest.getUserId());
            return ResponseEntity.ok().body(Map.of("user",userDto,"acccess_token",accessToken));

        }catch (HttpClientErrorException e){
            deleteUSer(id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error","Error al crear la cuenta , AccountService no esta funcionando","status",404));

        }

    }

}




