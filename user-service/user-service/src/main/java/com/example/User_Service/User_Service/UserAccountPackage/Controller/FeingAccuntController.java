package com.example.User_Service.User_Service.UserAccountPackage.Controller;


import com.example.User_Service.User_Service.UserAccountPackage.Model.Account;
import com.example.User_Service.User_Service.UserAccountPackage.Service.FeignClientService;
import feign.FeignException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class FeingAccuntController {
    @Autowired
    private FeignClientService feignClientService;

    @Operation(summary = "Get user account",
            description = "Retrieve the account associated with a specific userId. " +
                    "If the account does not exist, a 404 error will be returned."
    )
    @ApiResponse(responseCode = "200", description = "Account retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Account not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{userId}/accounts")
    public ResponseEntity<?> getUserAccount (@PathVariable("userId") String userId) {
           return  feignClientService.getUserAccount(userId);

    }

    @Operation(summary="Update user account",
            description = "Update the details of an existing account using the accountId. " +
                    "The request body should contain the updated account information."
    )
    @PatchMapping("{userId}/accounts/{accountId}")
    public ResponseEntity<?> updateAccount (@PathVariable("accountId") Long accountId, @RequestBody Account account){
        return feignClientService.updateAccount(accountId,account);
    }

}
