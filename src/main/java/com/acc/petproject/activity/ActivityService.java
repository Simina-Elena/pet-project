package com.acc.petproject.activity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ActivityService {
    List<Activity> getAllActivities();
    void addActivity(Activity activity, Long shelterId);
}
