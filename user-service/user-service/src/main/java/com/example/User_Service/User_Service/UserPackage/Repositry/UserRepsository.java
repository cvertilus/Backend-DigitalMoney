package com.example.User_Service.User_Service.UserPackage.Repositry;

import com.example.User_Service.User_Service.UserPackage.Model.User;
import org.springframework.stereotype.Repository;


public interface UserRepsository {
    User getUserById(String id);
    User getUserByUsername(String username);
    String cambioDeContrasena(String contraseña,String userId);
    void logout(String  userId);
}
