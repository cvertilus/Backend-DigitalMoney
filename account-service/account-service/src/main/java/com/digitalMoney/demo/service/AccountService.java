package com.digitalMoney.demo.service;

import com.digitalMoney.demo.Repository.AccountRepository;
import com.digitalMoney.demo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount (Account account){
        if(accountRepository.existsByUserId(account.getUserId()))
            throw new RuntimeException("la cuenta ya existe");
    return  accountRepository.save(account);
    }

    public Account getAccount (String userId){
        return accountRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("la cuenta no existe"));
    }


    public Account updateAccount(Long id, Account account){
        Optional<Account> account1 = accountRepository.findById(id);
        if(account1.isPresent()) {
            Account account2 = account1.get();
            account2.setCvu(account.getCvu());
            account2.setAlias(account.getAlias());
            account2.setUserId(account.getUserId());
            account2.setName(account.getName());
            account2.setBalance(account.getBalance());
            return accountRepository.save(account2);
        }else
            throw  new RuntimeException("Account no existe");
    }


}
