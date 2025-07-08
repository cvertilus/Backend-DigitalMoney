package com.example.User_Service.User_Service.UserActivityPackage.Repository;

import com.example.User_Service.User_Service.Configuration.FeignClientConfiguration;
import com.example.User_Service.User_Service.UserActivityPackage.Model.Activity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "activities-service",
        url = "http://localhost:8086",
        configuration = FeignClientConfiguration.class
)
public interface FeignClientAcitivity {

    @RequestMapping(method = RequestMethod.GET,value = "/activities")
    ResponseEntity<List<Activity>> getUserActivities (@RequestParam("userId") String userId);

    @RequestMapping(method = RequestMethod.POST, value = "/activities/{userId}")
    ResponseEntity<Activity> guardarActivity (@PathVariable("userId") String userId , @RequestBody Activity activity);

    @RequestMapping(method = RequestMethod.GET, value = "/activities/{activityId}")
    ResponseEntity<Activity> getActivityById (@PathVariable("activityId") String activityId);

}
