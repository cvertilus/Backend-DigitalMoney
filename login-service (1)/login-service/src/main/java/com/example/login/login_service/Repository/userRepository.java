package com.example.login.login_service.Repository;

import com.example.login.login_service.model.CreateUser;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface userRepository {
    List<?> getUsers();
    ResponseEntity<Map<String,Object>> loginUser(String username , String password);
    List<?> findByUsername(String username);
    ResponseEntity<Map<String ,Object>> registerUser (CreateUser createUser);

}
