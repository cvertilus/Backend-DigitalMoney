package com.example.User_Service.User_Service.UserAccountPackage.Service;

import com.example.User_Service.User_Service.UserAccountPackage.Model.Account;
import com.example.User_Service.User_Service.UserAccountPackage.Model.TransferenciaRequest;
import com.example.User_Service.User_Service.UserAccountPackage.Repository.FeignClientAccount;
import com.example.User_Service.User_Service.UserActivityPackage.Model.ActivityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FeignClientService {
    @Autowired
    private FeignClientAccount feignClientAccount;

    public ResponseEntity<Account> getUserAccount (String userId){
        return feignClientAccount.getUserAccount(userId);
    }

    public ResponseEntity<Account> updateAccount (Long accountId, Account account){
        return feignClientAccount.updateAccount(accountId,account);
    }

    public ResponseEntity<String> crearTransferencia (String userId, TransferenciaRequest transferenciaRequest){
        return feignClientAccount.transferActivity(transferenciaRequest);
    }


}
