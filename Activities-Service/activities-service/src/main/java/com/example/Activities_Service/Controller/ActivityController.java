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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activities")
@Validated
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    private void validarUserId(String userId ,String userIdDesdetoken) {
        if(userIdDesdetoken == null ) {
            return;
        }
        if (!userIdDesdetoken.equals(userId)) {
            throw new AccessDeniedException("El userId no tiene acceso");
        }
    }

    @Operation(summary = "Get activity by ID",
            description = "Retrieve an activity using its ID. If the activity does not exist, a 404 error will be returned."
    )
    @ApiResponse(responseCode = "200", description = "Activity retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Activity not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("/{activityId}")
    public ResponseEntity<Activity> getActivitiesById(@PathVariable("activityId") String activityId,@AuthenticationPrincipal Jwt jwt){
            Activity activity = activityService.getActivityById(activityId);
            validarUserId(activity.getUserId(), jwt != null ? jwt.getSubject() : null);
            return ResponseEntity.ok(activity);
    }

    @Operation(summary = "Get activities by user ID",
            description = "Retrieve all activities associated with a specific user ID. If no activities are found, an empty list will be returned."
    )
    @ApiResponse(responseCode = "200", description = "Activities retrieved successfully")
    @ApiResponse(responseCode = "404", description = "No activities found for the user")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping()
    public ResponseEntity<List<Activity>> getActivityByUserId(@RequestParam("userId") String userId, @AuthenticationPrincipal Jwt jwt){
        validarUserId(userId, jwt != null ? jwt.getSubject() : null);
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
    public ResponseEntity<Activity> createActivity(@Valid @RequestBody ActivityRequest activityRequest, @PathVariable("userId") String userId, @AuthenticationPrincipal Jwt jwt ){
        validarUserId(userId, jwt != null ? jwt.getSubject() : null);
        return ResponseEntity.ok(activityService.saveActivity(userId,activityRequest));
    }

    @Operation(summary = "Get activities by date range",
            description = "Retrieve all activities within a specified date range. The start and end dates should be in ISO-8601 format. \"2025-08-01T14:30:00\""
    )
    @ApiResponse(responseCode = "200", description = "Activities retrieved successfully")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid date format")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("{userId}/rango-fechas")
    public ResponseEntity<List<Activity>> getActivityByDateRange(@RequestParam("inicio") String inicio,
                                                                 @RequestParam("fin") String fin, @PathVariable ("userId") String userId,@AuthenticationPrincipal Jwt jwt) {
        validarUserId(userId, jwt != null ? jwt.getSubject() : null);
        List<Activity> activities = activityService.getActivityByDateRange(inicio, fin,userId);
        return ResponseEntity.ok(activities);
    }

    @Operation(summary = "Get  activities as a PDF",
            description = "Retrieve  activities and return them as a PDF file."
    )
    @ApiResponse(responseCode = "200", description = "PDF file generated successfully")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @GetMapping("{userId}/pdf")
    public ResponseEntity<byte[]> getActivityPdf (@PathVariable String userId, @AuthenticationPrincipal Jwt jwt) {
        validarUserId(userId, jwt != null ? jwt.getSubject() : null);
        byte[] pdfContent = activityService.getActivityLast10(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Disposition", "attachment; filename=activities.pdf")
                .body(pdfContent);

    }
}
