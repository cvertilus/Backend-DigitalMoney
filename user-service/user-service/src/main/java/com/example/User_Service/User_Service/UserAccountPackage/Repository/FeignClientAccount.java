package com.example.User_Service.User_Service.UserAccountPackage.Repository;

import com.example.User_Service.User_Service.Configuration.FeignClientConfiguration;
import com.example.User_Service.User_Service.UserAccountPackage.Model.Account;
import com.example.User_Service.User_Service.UserAccountPackage.Model.TransferenciaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "account-service",
        configuration = FeignClientConfiguration.class
)

public interface FeignClientAccount {
    @RequestMapping(method = RequestMethod.GET,value = "/accounts/{userId}")
    ResponseEntity <Account> getUserAccount (@PathVariable("userId") String userId);

    @RequestMapping(method = RequestMethod.PUT, value = "/accounts/{accountId}")
    ResponseEntity <Account> updateAccount (@PathVariable ("accountId") Long accountId, @RequestBody Account account);

    @RequestMapping(method = RequestMethod.POST, value = "/accounts/transfer")
    ResponseEntity <String> transferActivity (@RequestBody TransferenciaRequest transferenciaRequest);
}
