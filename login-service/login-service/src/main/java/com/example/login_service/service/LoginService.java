//package com.example.login_service.service;
//
//import com.example.login_service.repository.KeycloakRepository;
//import org.keycloak.representations.AccessTokenResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class LoginService {
//    @Autowired
//    private  KeycloakRepository keycloakRepository;
//
//    public String loginUser (String username , String password){
//        AccessTokenResponse tokenResponse = keycloakRepository.loginUser(username,password);
//        return tokenResponse.getToken();
//    }
//}
