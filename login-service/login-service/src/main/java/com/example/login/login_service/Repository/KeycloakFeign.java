package com.example.login.login_service.Repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "keycloak-client", url = "http://keycloak:8080/realms/backend-digital-money/protocol/openid-connect/token")
public interface KeycloakFeign {

    @RequestMapping(method =RequestMethod.POST)
    Map<String, Object> getToken (@RequestBody MultiValueMap<String, String> formData);


}
