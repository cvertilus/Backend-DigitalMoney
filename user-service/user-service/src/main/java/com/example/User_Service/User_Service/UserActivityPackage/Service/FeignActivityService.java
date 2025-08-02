package com.example.User_Service.User_Service.UserActivityPackage.Service;

import com.example.User_Service.User_Service.UserAccountPackage.Model.TransferenciaRequest;
import com.example.User_Service.User_Service.UserAccountPackage.Service.FeignClientService;

import com.example.User_Service.User_Service.UserActivityPackage.Model.Activity;
import com.example.User_Service.User_Service.UserActivityPackage.Model.ActivityRequest;
import com.example.User_Service.User_Service.UserActivityPackage.Repository.FeignClientAcitivity;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeignActivityService {
    @Autowired
    private FeignClientAcitivity feignClientAcitivity;

    @Autowired
    private FeignClientService feignClientService;

    public ResponseEntity<List<Activity>> getUserActivity (String userId){
        return feignClientAcitivity.getUserActivities(userId);
    }

    public ResponseEntity<Activity> getActivityById (String activityId){
        return  feignClientAcitivity.getActivityById(activityId);
    }
    public ResponseEntity<Activity> crearActivity (String userId, ActivityRequest activityRequest){
        TransferenciaRequest transferenciaRequest = new TransferenciaRequest();
        transferenciaRequest.setCantidad(activityRequest.getAmount());
        transferenciaRequest.setOrigin(activityRequest.getOrigin());
        transferenciaRequest.setDestino(activityRequest.getDestination());
        ResponseEntity<String> response = feignClientService.crearTransferencia(userId,transferenciaRequest);
       if (response.getStatusCode().is2xxSuccessful()){
           return  feignClientAcitivity.CrearActivity(userId, activityRequest);
       }

      return  ResponseEntity.badRequest().body(null);

    }
}
