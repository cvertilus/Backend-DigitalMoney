package com.digitalMoney.demo.restcontroler;

import com.digitalMoney.demo.model.Account;
import com.digitalMoney.demo.model.AccountRequest;
import com.digitalMoney.demo.model.TransferRequest;
import com.digitalMoney.demo.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@Validated
public class AccountController {
    @Autowired
    private AccountService accountService;


    private void validarUserId(String userId ,String userIdDesdetoken) {
        if(userIdDesdetoken == null ) {
           return;
        }
        if (!userIdDesdetoken.equals(userId)) {
            throw new AccessDeniedException("El userId no tiene acceso");
        }
    }

    @Operation(summary = "Get account by userId"
    , description = "Retrieve an account using the userId associated with it."
            + " If the account does not exist, a 404 error will be returned."
            + " The response will include the account details such as userId, cvu, alias, and balance."
    )
    @ApiResponse(responseCode = "200", description = "Account retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Account not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{userId}")
    public ResponseEntity<Account> getAccount(@PathVariable String userId,@AuthenticationPrincipal Jwt jwt) {
            if(jwt != null) {
                validarUserId(userId, jwt.getSubject());
            }

            Account account = accountService.getAccount(userId);
            return ResponseEntity.ok(account);
    }


    @Operation(summary = "Create a new account" , description = "Create a new account with the provided details. " +
            "The account will be associated with a userId and can have an optional alias or cvu.")
    @ApiResponse(responseCode = "200", description = "Account created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid input data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping()
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountRequest accountRequest,@AuthenticationPrincipal Jwt jwt){
            Account account = accountService.createAccount(accountRequest);
            return ResponseEntity.ok(account);
    }


    @Operation(summary = "Update an existing account", description = "Update the details of an existing account using the accountId. " +
            "The request body should contain the updated account information.")
    @ApiResponse(responseCode = "200", description = "Account updated successfully")
    @ApiResponse(responseCode = "404", description = "Account not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PutMapping("/{accountId}")
    public ResponseEntity<Account> updateAccount (@PathVariable Long accountId ,@Valid @RequestBody Account account,@AuthenticationPrincipal Jwt jwt){
            validarUserId(account.getUserId(),
                    jwt != null ? jwt.getSubject() : null);
            Account account1 = accountService.updateAccount(accountId,account);
            return ResponseEntity.ok(account1);

    }

    @Operation(summary = "Transfer money between accounts", description = "Transfer money from one account to another. " +
            "The request body should contain the transfer details including source account, destination account, and amount.")
    @ApiResponse(responseCode = "200", description = "Transfer successful")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid transfer details")
    @ApiResponse(responseCode = "404", description = "Account not found")
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer (@Valid @RequestBody TransferRequest transferRequest,@AuthenticationPrincipal Jwt jwt) throws BadRequestException {
       validarTransferRequest(transferRequest,jwt);
        String resultado = accountService.createActivity(transferRequest);
        return ResponseEntity.ok().body("exitoso");
    }

    private void validarTransferRequest(TransferRequest transferRequest, Jwt jwt) {
        Optional<Account>  account = accountService.getAccountByCvuOrAlias(transferRequest.getOrigin(),transferRequest.getOrigin());
      if(account.isPresent()) {
            String userId = account.get().getUserId();
            if(jwt != null) {
                validarUserId(userId, jwt.getSubject());
            }
            return;
        } else {
            throw new EntityNotFoundException("Cuenta origen no encontrada");
      }
    }

}
