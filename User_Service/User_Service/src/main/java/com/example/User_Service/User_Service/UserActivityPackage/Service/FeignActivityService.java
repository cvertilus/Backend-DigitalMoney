package com.example.User_Service.User_Service.UserActivityPackage.Service;

import com.example.User_Service.User_Service.UserAccountPackage.Repository.FeignClientAccount;
import com.example.User_Service.User_Service.UserActivityPackage.Model.Activity;
import com.example.User_Service.User_Service.UserActivityPackage.Model.Transfer;
import com.example.User_Service.User_Service.UserActivityPackage.Repository.FeignClientAcitivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeignActivityService {
    @Autowired
    private FeignClientAcitivity feignClientAcitivity;

    @Autowired
    private FeignClientAccount feignClientAccount;

    public ResponseEntity<List<Activity>> getUserActivity (String userId){
        return feignClientAcitivity.getUserActivities(userId);
    }

    public ResponseEntity<Activity> getActivityById (String activityId){
        return  feignClientAcitivity.getActivityById(activityId);
    }
    public ResponseEntity<Activity> guardarActivity (String userId, Activity activity){
        Transfer transfer =  Transfer.builder()
                .cantitdad(activity.getAmount())
                .origin(activity.getOrigin())
                .destino(activity.getDestination())
                .build();
        ResponseEntity  r = feignClientAccount.transferActivity(transfer);
        if(r.getStatusCode().is2xxSuccessful()) return feignClientAcitivity.guardarActivity(userId, activity);

        return ResponseEntity.badRequest().body(null);
    }
}
