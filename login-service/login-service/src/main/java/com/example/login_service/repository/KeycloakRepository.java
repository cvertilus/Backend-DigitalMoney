//package com.example.login_service.repository;
//
//import com.example.login_service.configuration.KeycloakClientConfiguration;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.keycloak.representations.AccessTokenResponse;
//import org.springframework.stereotype.Repository;
//
//import javax.ws.rs.NotAuthorizedException;
//@Repository
//public class KeycloakRepository implements IUserRepository {
//
//    private KeycloakClientConfiguration keycloakClientConfiguration;
//
//
//    @Override
//    public AccessTokenResponse loginUser (String name , String password){
//        try {
//            return KeycloakBuilder.builder()
//                    .serverUrl(keycloakClientConfiguration.getServerurl())
//                    .realm(keycloakClientConfiguration.getRealm())
//                    .clientId(keycloakClientConfiguration.getClientid())
//                    .clientSecret(keycloakClientConfiguration.getClientsecret())
//                    .username(name)
//                    .password(password)
//                    .grantType("password")
//                    .build()
//                    .tokenManager()
//                    .getAccessToken();
//        }catch (NotAuthorizedException e){
//            throw new RuntimeException( "Usuario o Contraseña Incorrectos");
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//
//    }
//}
