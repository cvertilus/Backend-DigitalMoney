package com.digitalMoney.demo.service;

import com.digitalMoney.demo.Repository.AccountRepository;
import com.digitalMoney.demo.model.Account;
import com.digitalMoney.demo.model.AccountRequest;
import com.digitalMoney.demo.model.CvuAliasGenerator;
import com.digitalMoney.demo.model.TransferRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EntityNotFoundException;


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
    public Account createAccount (AccountRequest request){
        if(accountRepository.existsByUserId(request.getUserId()))
            throw new IllegalArgumentException("la cuenta ya existe");
        Account account = createAccountFromRequest(request);
        return  accountRepository.save(account);
    }

    private Account createAccountFromRequest(AccountRequest request){
        CvuAliasGenerator cvuAliasGenerator = new CvuAliasGenerator();
        String alias = cvuAliasGenerator.getAlias();
        String cvu = cvuAliasGenerator.getCvu();
        Account account = new Account();
        account.setBalance(0);
        account.setName(request.getName());
        account.setCvu(cvu);
        account.setAlias(alias);
        account.setUserId(request.getUserId());
        return  account;
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
    public String createActivity (TransferRequest transferRequest){
        Account accountOrigin = validarAccount(transferRequest.getOrigin());
        Account accountDestino = validarAccount(transferRequest.getDestino());
        if (accountDestino == null ) throw new EntityNotFoundException("los datos de  destino son incorrectos , no Existe la cuenta ");
        if (accountOrigin.getName().equals(accountDestino.getName())) {
            // Si el origen y destino son iguales, se trata de un depósito
           accountOrigin =  createDepostito(accountOrigin , transferRequest.getCantidad());
           return "Ok";
        }
        return createTransfer(accountOrigin,accountDestino,transferRequest.getCantidad());

    }


    private String createTransfer(Account accountOrigin, Account accountDestino, int cantitad) {
        Account accountO = createDepostito(accountOrigin, cantitad * (-1) );
        Account accountD = createDepostito(accountDestino,cantitad);
        return "ok";
    }

    private Account createDepostito(Account accountOrigin, int cantitad) {
        int cantidadActual = accountOrigin.getBalance();
        Account account = accountOrigin;
        if(cantidadActual + cantitad < 0)
            throw new IllegalArgumentException("No se puede realizar la transferencia, saldo insuficiente");
        account.setBalance(cantidadActual + cantitad);
        return accountRepository.save(account);

    }

    private Account validarAccount (String dato){
        Account account = getAccountByCvuOrAlias(dato,dato).orElse( null);
        return account;

    }

}
