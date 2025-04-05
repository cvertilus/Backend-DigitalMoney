package com.example.User_Service.User_Service.UserPackage.Repositry;

import com.example.User_Service.User_Service.UserPackage.Model.User;
import jakarta.validation.constraints.Null;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class KeycloakRepository implements UserRepsository{

    @Autowired
    private Keycloak keycloak;
    @Value("${app.keycloak.realm}")
    String realm;
    @Value("${app.keycloak.clientid}")
    String clientid;
    @Value("${app.keycloak.clientsecret}")
    String clientsecret;


    @Override
    public User getUserById(String id) {
       UserRepresentation userRepresentation = keycloak
                .realm(realm)
                .users()
               .get(id)
               .toRepresentation();

       return getUSer(userRepresentation);
    }

    private User getUSer(UserRepresentation userRepresentation) {
        User user = User.builder()
                .id(userRepresentation.getId())
                .email(userRepresentation.getEmail())
                .firstname(userRepresentation.getFirstName())
                .lastname(userRepresentation.getLastName())
                .username(userRepresentation.getUsername())
                .build();
        Map<String, List<String>> attributes = userRepresentation.getAttributes();
        user.setDni(attributes.get("dni").get(0));
        user.setTelefono(attributes.get("phone").get(0));
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        List<UserRepresentation> userRepresentations =  keycloak
                .realm(realm)
                .users()
                .search(username);
        if(userRepresentations.isEmpty()) return null;
        return getUSer(userRepresentations.get(0));
    }

    @Override
    public String cambioDeContrasena(String contraseña,String userId) {
        try {
            CredentialRepresentation credentialRepresentation = nuevaContraseña(contraseña);
            keycloak.realm(realm).users().get(userId).resetPassword(credentialRepresentation);
            return "cambio de contraseña Exitosa";

        }catch (javax.ws.rs.WebApplicationException e){
            return " Error "+ e.getResponse().getStatus();
        }
    }

    @Override
    public String logout(String token) {

        return "";
    }

    private CredentialRepresentation nuevaContraseña  (String contraseña){
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(contraseña);
        credentialRepresentation.setTemporary(false);
        return  credentialRepresentation;
    }
}
