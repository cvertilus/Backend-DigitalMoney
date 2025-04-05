package com.example.Activities_Service.Service;

import com.example.Activities_Service.Model.Activity;
import com.example.Activities_Service.Model.ActivityRequest;
import com.example.Activities_Service.Repository.ActivityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public Activity saveActivity(String userId, ActivityRequest activityRequest){
        Activity activity = Activity.builder()
                .amount(activityRequest.getAmount())
                .type(activityRequest.getType())
                .name(activityRequest.getName())
                .userId(userId)
                .description(activityRequest.getDescription())
                .build();
       if (activityRequest.getType().equals("Transfer")){
           activity.setOrigin(activityRequest.getOrigin());
           activity.setDestination(activityRequest.getDestination());
       }
        return activityRepository.save(activity);
    }

    public Activity getActivityById(String id){
        return activityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El Actividad no exite "+id));
    }
    public List<Activity> getActivityByUserId(String userId){

        return activityRepository.findAllByUserId(userId);
    }



}
