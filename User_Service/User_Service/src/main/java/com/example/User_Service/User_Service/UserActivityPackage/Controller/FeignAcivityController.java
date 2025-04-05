package com.example.User_Service.User_Service.UserActivityPackage.Controller;

import com.example.User_Service.User_Service.UserActivityPackage.Model.Activity;
import com.example.User_Service.User_Service.UserActivityPackage.Service.FeignActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/users")
public class FeignAcivityController {

    @Autowired
    private FeignActivityService feignActivityService;


    @GetMapping("/{userId}/activities/{activityId}")
    public ResponseEntity<Activity> getActivity (@PathVariable ("activityId") String activityId){
        return feignActivityService.getActivityById(activityId);
    }

    @GetMapping("/{userId}/activities")
    public ResponseEntity<List<Activity>> getUSerActivity (@PathVariable("userId") String userId){
        return feignActivityService.getUserActivity(userId);
    }

    @PostMapping("/{userId}/activities")
    public ResponseEntity<Activity> createActivity (@PathVariable("userId") String userId, @RequestBody Activity activity){
        return feignActivityService.guardarActivity(userId, activity);
    }

}
