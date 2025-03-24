package com.example.User_Service.User_Service.UserPackage.Repositry;

import com.example.User_Service.User_Service.UserPackage.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepsository {
    User getUserById(String id);
    User getUserByUsername(String username);
    String cambioDeContrasena(String contraseña,String userId);
    String logout(String token);
}
