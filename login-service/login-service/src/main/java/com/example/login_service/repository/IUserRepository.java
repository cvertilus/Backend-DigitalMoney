package com.example.login_service.repository;

import com.example.login_service.model.User;
import org.keycloak.representations.AccessTokenResponse;

import java.util.Optional;

public interface IUserRepository {
AccessTokenResponse loginUser (String username , String passwrord);
}
