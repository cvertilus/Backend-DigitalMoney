package com.example.Activities_Service.Controller;

import com.example.Activities_Service.Model.Activity;
import com.example.Activities_Service.Model.ActivityRequest;
import com.example.Activities_Service.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @GetMapping("/{activityId}")
    public ResponseEntity<Activity> getActivitiesById(@PathVariable("activityId") String activityId){
            Activity activity = activityService.getActivityById(activityId);
            return ResponseEntity.ok(activity);
    }

    @GetMapping()
    public ResponseEntity<List<Activity>> getActivityByUserId(@RequestParam("userId") String userId){
        List<Activity> activities = activityService.getActivityByUserId(userId);
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Activity> createActivity(@RequestBody ActivityRequest activityRequest,@PathVariable("userId") String userId ){
        return ResponseEntity.ok(activityService.saveActivity(userId,activityRequest));
    }
}
