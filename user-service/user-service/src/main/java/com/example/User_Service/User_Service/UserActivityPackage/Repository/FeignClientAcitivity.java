package com.example.User_Service.User_Service.UserActivityPackage.Repository;

import com.example.User_Service.User_Service.Configuration.FeignClientConfiguration;
import com.example.User_Service.User_Service.UserActivityPackage.Model.Activity;
import com.example.User_Service.User_Service.UserActivityPackage.Model.ActivityRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "activities-service",
        configuration = FeignClientConfiguration.class
)
public interface FeignClientAcitivity {

    @RequestMapping(method = RequestMethod.GET,value = "/activities")
    ResponseEntity<List<Activity>> getUserActivities (@RequestParam("userId") String userId);

    @RequestMapping(method = RequestMethod.POST, value = "/activities/{userId}")
    ResponseEntity<Activity> CrearActivity(@PathVariable("userId") String userId , @RequestBody ActivityRequest activityRequest);

    @RequestMapping(method = RequestMethod.GET, value = "/activities/{activityId}")
    ResponseEntity<Activity> getActivityById (@PathVariable("activityId") String activityId);

    @RequestMapping(method = RequestMethod.GET, value = "/activities/{userId}/rango-fechas")
    ResponseEntity<List<Activity>> getActivityByDateRange(@RequestParam("inicio") String inicio,
                                                          @RequestParam("fin") String fin,
                                                          @PathVariable("userId") String userId);


    @RequestMapping(method = RequestMethod.GET, value = "/activities/{userId}/pdf")
    ResponseEntity<byte[]> getActivityLast10(@PathVariable("userId") String userId);
}
