package com.example.Activities_Service.Controller;

import com.example.Activities_Service.Model.Activity;
import com.example.Activities_Service.Model.ActivityRequest;
import com.example.Activities_Service.Service.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
@Validated
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @Operation(summary = "Get activity by ID",
            description = "Retrieve an activity using its ID. If the activity does not exist, a 404 error will be returned."
    )
    @ApiResponse(responseCode = "200", description = "Activity retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Activity not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{activityId}")
    public ResponseEntity<Activity> getActivitiesById(@PathVariable("activityId") String activityId){
            Activity activity = activityService.getActivityById(activityId);
            return ResponseEntity.ok(activity);
    }

    @Operation(summary = "Get activities by user ID",
            description = "Retrieve all activities associated with a specific user ID. If no activities are found, an empty list will be returned."
    )
    @ApiResponse(responseCode = "200", description = "Activities retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No activities found for the user")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping()
    public ResponseEntity<List<Activity>> getActivityByUserId(@RequestParam("userId") String userId){
        List<Activity> activities = activityService.getActivityByUserId(userId);
        return ResponseEntity.ok(activities);
    }


    @Operation(summary = "Create a new activity",
            description = "Create a new activity for a user. The request body should contain the activity details."
    )
    @ApiResponse(responseCode = "200", description = "Activity created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid input data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/{userId}")
    public ResponseEntity<Activity> createActivity(@Valid @RequestBody ActivityRequest activityRequest, @PathVariable("userId") String userId ){
        return ResponseEntity.ok(activityService.saveActivity(userId,activityRequest));
    }
}
