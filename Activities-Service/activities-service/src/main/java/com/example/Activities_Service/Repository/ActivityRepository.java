package com.example.Activities_Service.Repository;

import com.example.Activities_Service.Model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,String> {
    List<Activity> findAllByUserId(String userid);

    List<Activity> findAllByUserIdAndDateBetween(String userid, LocalDateTime start, LocalDateTime end);
    List<Activity> findTop10AllByUserIdOrderByDateDesc(String userid);
}
