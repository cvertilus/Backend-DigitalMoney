package com.digitalMoney.demo.restcontroler;

import com.digitalMoney.demo.model.Account;
import com.digitalMoney.demo.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("{userId}")
    public ResponseEntity<?> getAccount(@PathVariable String userId){
        try {
            Account account = accountService.getAccount(userId);
            return ResponseEntity.ok(account);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cuenta no encontrada");

        }catch (IllegalArgumentException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos invalidos");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping()
    public ResponseEntity<?> createAccount(@RequestBody Account account){
        try {
            Account account1 = accountService.createAccount(account);
            return ResponseEntity.ok(account1);
        }catch (IllegalArgumentException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Datos invalidos");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping("{saccountId}")
    public ResponseEntity<?> updateAccount (@PathVariable Long accountId , @RequestBody Account account){
        try {
            Account account1 = accountService.updateAccount(accountId,account);
            return ResponseEntity.ok(account1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



}
