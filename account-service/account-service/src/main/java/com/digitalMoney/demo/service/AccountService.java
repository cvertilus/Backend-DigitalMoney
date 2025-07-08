package com.digitalMoney.demo.service;

import com.digitalMoney.demo.Repository.AccountRepository;
import com.digitalMoney.demo.model.Account;
import com.digitalMoney.demo.model.TransferRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityNotFoundException;

import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AccountService {
    @Autowired
    private AccountRepository accountRepository;


    @Schema(
            description = "Create a new account",
            example = "Account object with userId, cvu, alias, name, and balance"
    )
    public Account createAccount (Account account){
        if(accountRepository.existsByUserId(account.getUserId()))
            throw new IllegalArgumentException("la cuenta ya existe");
    return  accountRepository.save(account);
    }

    @Schema(
            description = "Get an account by userId",
            example = "Account object with userId, cvu, alias, name, and balance"
    )
    public Account getAccount (String userId){
        return accountRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("El userId : " + userId + " no existe"));
    }


    @Schema(
            description = "Update an existing account",
            example = "Updated Account object with userId, cvu, alias, name, and balance"
    )
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
            throw  new EntityNotFoundException("El account id: " + id + "no existe");
    }

    @Schema(
            description = "Get an account by CVU or Alias",
            example = "Optional<Account> object with userId, cvu, alias, name, and balance"
    )
    public Optional<Account> getAccountByCvuOrAlias (String cvu , String alias){
        return accountRepository.findByCvuOrAlias(cvu, alias);
    }

    @Schema(
            description = "Create a transfer or deposit activity",
            example = "String indicating the result of the operation"
    )
    public String createActivity (TransferRequest transferRequest) throws BadRequestException {
        Account accountOrigin = validarAccount(transferRequest.getOrigin());
        Account accountDestino = validarAccount(transferRequest.getDestino());
        if(accountOrigin == null && accountDestino== null)  throw new BadRequestException("debe exisitir un Account") ;
        if (accountDestino == null) {
           accountOrigin =  createDepostito(accountOrigin , transferRequest.getCantitad());
           return "Ok";
        }
        return createTransfer(accountOrigin,accountDestino,transferRequest.getCantitad());

    }


    private String createTransfer(Account accountOrigin, Account accountDestino, int cantitad) {
        Account accountO = createDepostito(accountOrigin, cantitad * (-1) );
        Account accountD = createDepostito(accountDestino,cantitad);
        return "ok";
    }

    private Account createDepostito(Account accountOrigin, int cantitad) {
        int cantidadActual = accountOrigin.getBalance();
        accountOrigin.setBalance(cantidadActual + cantitad);
        return accountRepository.save(accountOrigin);

    }

    private Account validarAccount (String dato){
        Account account = getAccountByCvuOrAlias(dato,dato).orElse( null);
        return account;

    }

}
