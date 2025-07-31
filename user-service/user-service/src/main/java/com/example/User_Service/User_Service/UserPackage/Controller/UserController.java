package com.example.User_Service.User_Service.UserPackage.Controller;

import com.example.User_Service.User_Service.UserPackage.Model.User;
import com.example.User_Service.User_Service.UserPackage.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    public UserController (UserService userService){
        this.userService = userService;
    }

    @Operation(summary = "Get user by userId",
            description = "Retrieve a user using the userId associated with it. "
                    + "If the user does not exist, a 404 error will be returned. "
                    + "The response will include the user details such as userId, username, and email.")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById (@PathVariable("userId") String userId){
        User user = userService.getUserById(userId);
       return ResponseEntity.ok(user);
    }


    @Operation(summary = "Get user by username",
            description = "Retrieve a user using the username associated with it. "
                    + "If the user does not exist, a 404 error will be returned. "
                    + "The response will include the user details such as userId, username, and email.")
    @ApiResponse(responseCode = "200", description = "User retrieved successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping()
    public ResponseEntity <?> getUserByUsername (@RequestParam("username") String username){
        User user = userService.getUserByUsername(username);
        if(user == null) return  ResponseEntity.ok("No exite el usuario");
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "LogOut user",
            description = "logout a user using the token associated with it. ")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized, invalid token")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/logout")
    public ResponseEntity<?> logout (@AuthenticationPrincipal Jwt jwt){
        String userId = jwt.getSubject();
        userService.logout(userId);
        return ResponseEntity.ok("logout existoso");
    }


}
