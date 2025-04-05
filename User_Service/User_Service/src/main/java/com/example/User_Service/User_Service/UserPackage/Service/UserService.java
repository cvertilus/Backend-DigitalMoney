package com.example.User_Service.User_Service.UserPackage.Service;

import com.example.User_Service.User_Service.UserPackage.Model.User;
import com.example.User_Service.User_Service.UserPackage.Repositry.UserRepsository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired(required = true)
    private final UserRepsository userRepsository;

    public UserService(UserRepsository userRepsository) {
        this.userRepsository = userRepsository;
    }

    public User getUserById (String id) {
        return userRepsository.getUserById(id);
    }

    public User getUserByUsername (String username){
        return userRepsository.getUserByUsername(username);
    }

    public String updateContraseña (String contraseña ,String id){
        return userRepsository.cambioDeContrasena(contraseña,id);
    }
}
