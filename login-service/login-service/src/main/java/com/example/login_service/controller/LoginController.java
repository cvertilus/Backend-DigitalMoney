package com.example.login_service.controller;

//import com.example.login_service.model.User;
//import com.example.login_service.service.LoginService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
//    @Autowired
//    private LoginService loginService;

    @PostMapping ("/hola")
    public String hole (){
          return  "hola bay ";
    }


//    @PostMapping("/login")
//    public ResponseEntity<?> login (@RequestBody User user){
//        try{
//            String token = loginService.loginUser(user.getUsername(),user.getPassword());
//            return ResponseEntity.ok(token);
//        }catch (IllegalArgumentException e){
//            return ResponseEntity.badRequest().body("{\\\"error\\\": \\\"Invalid username or password\\\"}}");
//        } catch (Exception e) {
//            return  ResponseEntity.internalServerError().body("\"{\\\"error\\\": \\\"Internal server error\\\"}\"");
//        }
//    }
}
