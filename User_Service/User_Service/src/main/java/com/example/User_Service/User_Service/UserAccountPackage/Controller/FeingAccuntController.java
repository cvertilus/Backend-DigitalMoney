package com.example.User_Service.User_Service.UserAccountPackage.Controller;


import com.example.User_Service.User_Service.UserAccountPackage.Model.Account;
import com.example.User_Service.User_Service.UserAccountPackage.Service.FeignClientService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class FeingAccuntController {
    @Autowired
    private FeignClientService feignClientService;

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<?> getUserAccount (@PathVariable("userId") String userId) {
           return  feignClientService.getUserAccount(userId);

    }

    @PatchMapping("{userId}/accounts/{accountId}")
    public ResponseEntity<?> updateAccount (@PathVariable("accountId") Long accountId, @RequestBody Account account){
        return feignClientService.updateAccount(accountId,account);
    }

}
