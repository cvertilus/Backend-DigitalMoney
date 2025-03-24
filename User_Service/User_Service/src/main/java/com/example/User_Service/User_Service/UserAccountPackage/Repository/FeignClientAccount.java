package com.example.User_Service.User_Service.UserAccountPackage.Repository;

import com.example.User_Service.User_Service.Configuration.FeignClientConfiguration;
import com.example.User_Service.User_Service.UserAccountPackage.Model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        name = "account-service",
        url = "http://localhost:8084",
        configuration = FeignClientConfiguration.class
)

public interface FeignClientAccount {
    @RequestMapping(method = RequestMethod.GET,value = "/accounts/{userId}")
    ResponseEntity <Account> getUserAccount (@PathVariable("userId") String userId);

    @RequestMapping(method = RequestMethod.PATCH , value = "/accounts/{accountId}")
    ResponseEntity <Account> updateAccount (@PathVariable ("accountId") Long accuntId, @RequestBody Account account);
}
