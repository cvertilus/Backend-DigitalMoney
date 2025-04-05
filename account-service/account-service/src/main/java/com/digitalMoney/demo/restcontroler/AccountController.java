package com.digitalMoney.demo.restcontroler;

import com.digitalMoney.demo.model.Account;
import com.digitalMoney.demo.model.TransferRequest;
import com.digitalMoney.demo.service.AccountService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("{userId}")
    public ResponseEntity<Account> getAccount(@PathVariable String userId){
            Account account = accountService.getAccount(userId);
            return ResponseEntity.ok(account);
    }

    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
            Account account1 = accountService.createAccount(account);
            return ResponseEntity.ok(account1);
    }

    @PatchMapping("{saccountId}")
    public ResponseEntity<Account> updateAccount (@PathVariable Long accountId , @RequestBody Account account){
            Account account1 = accountService.updateAccount(accountId,account);
            return ResponseEntity.ok(account1);

    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer (@RequestBody TransferRequest transferRequest)  {
        String resultado = accountService.createActivity(transferRequest);
        return ResponseEntity.ok().body("exitoso");
    }



}
