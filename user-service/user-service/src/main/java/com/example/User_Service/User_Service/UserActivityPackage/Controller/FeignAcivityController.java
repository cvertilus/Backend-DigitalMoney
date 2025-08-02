package com.example.User_Service.User_Service.UserActivityPackage.Controller;

import com.example.User_Service.User_Service.UserActivityPackage.Model.Activity;
import com.example.User_Service.User_Service.UserActivityPackage.Model.ActivityRequest;
import com.example.User_Service.User_Service.UserActivityPackage.Service.FeignActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@Validated
@RequestMapping("/users")
public class FeignAcivityController {

    @Autowired
    private FeignActivityService feignActivityService;

   @Operation(summary = "Get a specific activity for a user",
            description = "Retrieve a specific activity by activityId associated with a user. " +
                    "If the activity does not exist, a 404 error will be returned.")
    @ApiResponse(responseCode = "200", description = "Activity retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Activity not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{userId}/activities/{activityId}")
    public ResponseEntity<Activity> getActivity (@PathVariable ("activityId") String activityId){
        return feignActivityService.getActivityById(activityId);
    }


    @Operation(summary = "Get all activities for a user",
            description = "Retrieve all activities associated with a specific user by userId. " +
                    "If the user does not have any activities, an empty list will be returned.")
    @ApiResponse(responseCode = "200", description = "Activities retrieved successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{userId}/activities")
    public ResponseEntity<List<Activity>> getUSerActivity (@PathVariable("userId") String userId){
        return feignActivityService.getUserActivity(userId);
    }

    @Operation(summary = "Create a new activity for a user",
            description = "Create a new activity associated with a specific user by userId. " +
                    "The request body should contain the activity details.")
    @ApiResponse(responseCode = "201", description = "Activity created successfully")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid input data")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @PostMapping("/{userId}/activities")
    public ResponseEntity<Activity> createActivity (@PathVariable("userId") String userId, @Valid @RequestBody ActivityRequest activityRequest){
        return feignActivityService.crearActivity(userId, activityRequest);
    }

}
