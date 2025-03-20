//package com.example.login_service.configuration;
//
//import org.keycloak.OAuth2Constants;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//
//@Component
//@ConfigurationProperties(prefix = "keycloak")
//public class KeycloakClientConfiguration {
//    @Value("${keycloak.realm}")
//    private String realm;
//    @Value("${keycloak.serverurl}")
//    private String serverurl;
//    @Value("${keycloak.clientid}")
//    private String clientid;
//    @Value("${keycloak.clientsecret}")
//    private String clientsecret;
//
//    @Bean
//    public Keycloak getInstance() {
//        return KeycloakBuilder.builder()
//                .serverUrl(serverurl)
//                .realm(realm)
//                .clientId(clientid)
//                .clientSecret(clientsecret)
//                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
//                .build();
//    }
//
//    public String getRealm() {
//        return realm;
//    }
//
//    public void setRealm(String realm) {
//        this.realm = realm;
//    }
//
//    public String getServerurl() {
//        return serverurl;
//    }
//
//    public void setServerurl(String serverurl) {
//        this.serverurl = serverurl;
//    }
//
//    public String getClientid() {
//        return clientid;
//    }
//
//    public void setClientid(String clientid) {
//        this.clientid = clientid;
//    }
//
//    public String getClientsecret() {
//        return clientsecret;
//    }
//
//    public void setClientsecret(String clientsecret) {
//        this.clientsecret = clientsecret;
//    }
//}