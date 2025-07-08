package com.digitalMoney.demo.restcontroler;

import com.digitalMoney.demo.model.Account;
import com.digitalMoney.demo.model.TransferRequest;
import com.digitalMoney.demo.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Operation(summary = "Get account by userId"
    , description = "Retrieve an account using the userId associated with it."
            + " If the account does not exist, a 404 error will be returned."
            + " The response will include the account details such as userId, cvu, alias, and balance."
    )
    @ApiResponse(responseCode = "200", description = "Account retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Account not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("{userId}")
    public ResponseEntity<Account> getAccount(@PathVariable String userId){
            Account account = accountService.getAccount(userId);
            return ResponseEntity.ok(account);
    }


    @Operation(summary = "Create a new account" , description = "Create a new account with the provided details. " +
            "The account will be associated with a userId and can have an optional alias or cvu.")
    @ApiResponse(responseCode = "200", description = "Account created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid input data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
            Account account1 = accountService.createAccount(account);
            return ResponseEntity.ok(account1);
    }


    @Operation(summary = "Update an existing account", description = "Update the details of an existing account using the accountId. " +
            "The request body should contain the updated account information.")
    @ApiResponse(responseCode = "200", description = "Account updated successfully")
    @ApiResponse(responseCode = "404", description = "Account not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PatchMapping("{saccountId}")
    public ResponseEntity<Account> updateAccount (@PathVariable Long accountId , @RequestBody Account account){
            Account account1 = accountService.updateAccount(accountId,account);
            return ResponseEntity.ok(account1);

    }

    @Operation(summary = "Transfer money between accounts", description = "Transfer money from one account to another. " +
            "The request body should contain the transfer details including source account, destination account, and amount.")
    @ApiResponse(responseCode = "200", description = "Transfer successful")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid transfer details")
    @ApiResponse(responseCode = "404", description = "Account not found")
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer (@RequestBody TransferRequest transferRequest) throws BadRequestException {
        String resultado = accountService.createActivity(transferRequest);
        return ResponseEntity.ok().body("exitoso");
    }



}
