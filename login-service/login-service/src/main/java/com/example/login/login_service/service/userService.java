package com.example.login.login_service.service;

import com.example.login.login_service.Repository.userRepository;
import com.example.login.login_service.model.CreateUser;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class userService {

    private userRepository userRepository;

    public userService(userRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<?> getUsers (){
       return userRepository.getUsers();
    }

    public ResponseEntity<Map<String, Object>> loginUser(String username , String password){
        return userRepository.loginUser(username,password);
    }

    public List<?> getUserByName (String username ){
        return userRepository.findByUsername(username);
    }

    public ResponseEntity<Map<String,Object>> registerUser (CreateUser user){
        return userRepository.registerUser(user);
    }

}
