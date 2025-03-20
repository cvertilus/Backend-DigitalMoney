package com.example.login.login_service.controller;

import com.example.login.login_service.model.LoginUserBody;
import com.example.login.login_service.model.CreateUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.login.login_service.service.userService;

import java.util.List;
@RestController
public class LoginController {


    private  userService userService;
    public LoginController(userService userService){
        this.userService = userService;
    }


    @PostMapping("/hola")
    public String hole (){
        return  "hola bay ";
    }

    @GetMapping("/")
    public List<?> getUsers (){
        return userService.getUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginUserBody loginUserBody){
       return  userService.loginUser(loginUserBody.getUsername(),loginUserBody.getPassword());
    }

    @GetMapping("/{username}")
    public  ResponseEntity<?> getUserByName (@PathVariable String username){
     List<?> userRepresentation=  userService.getUserByName(username);
     return  ResponseEntity.ok( userRepresentation);

    }
    @PostMapping("/register")
    public ResponseEntity<?> createUser (@RequestBody CreateUser createUser ){
        return userService.registerUser(createUser);
    }
}
