package com.example.login.login_service.Repository;

import com.example.login.login_service.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "account-service",url = "http://localhost:8084")
public interface AccountFeignClient {

     @RequestMapping(method =RequestMethod.POST,value = "/accounts")
     ResponseEntity<?> createAccount(@RequestBody Account account, @RequestHeader("Authorization") String token);
}
