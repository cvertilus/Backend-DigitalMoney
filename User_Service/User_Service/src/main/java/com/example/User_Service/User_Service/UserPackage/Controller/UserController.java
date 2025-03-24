package com.example.User_Service.User_Service.UserPackage.Controller;

import com.example.User_Service.User_Service.UserPackage.Model.User;
import com.example.User_Service.User_Service.UserPackage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserById (@PathVariable("userId") String userId){
        User user = userService.getUserById(userId);
       return ResponseEntity.ok(user);
    }

    @GetMapping("")
    public ResponseEntity <?> getUserByUsername (@RequestParam("username") String username){
        User user = userService.getUserByUsername(username);
        if(user == null) return  ResponseEntity.ok("No exite el usuario");
        return ResponseEntity.ok(user);
    }

}
