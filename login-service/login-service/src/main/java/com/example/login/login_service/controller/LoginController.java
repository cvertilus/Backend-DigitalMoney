package com.example.login.login_service.controller;

import com.example.login.login_service.model.LoginUserBody;
import com.example.login.login_service.model.CreateUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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



    @GetMapping("/")
    private List<?> getUsers (){
        return userService.getUsers();
    }

    @Operation(summary = "User login",
            description = "Authenticate a user with their username and password. If the credentials are valid, a token will be returned.")
    @ApiResponse(responseCode = "200", description = "User logged in successfully")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginUserBody loginUserBody){
       return  userService.loginUser(loginUserBody.getUsername(),loginUserBody.getPassword());
    }


    @GetMapping("/{username}")
    private ResponseEntity<?> getUserByName (@PathVariable String username){
     List<?> userRepresentation=  userService.getUserByName(username);
     return  ResponseEntity.ok( userRepresentation);

    }

    @Operation(summary = "Register a new user",
            description = "Create a new user with the provided details. The user will be registered in the system.")
    @ApiResponse(responseCode = "201", description = "User registered successfully")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid input data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/register")
    public ResponseEntity<?> createUser (@RequestBody CreateUser createUser ){
        return userService.registerUser(createUser);
    }
}
