package com.acc.petproject.activity;

import com.acc.petproject.shelter.Shelter;
import com.acc.petproject.shelter.ShelterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActivityServiceImpl implements ActivityService{

    private ActivityRepository activityRepository;
    private ShelterRepository shelterRepository;

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public void addActivity(Activity activity, Long shelterId) {
        Optional<Shelter> shelter = shelterRepository.findById(shelterId);
        Activity activity1 = new Activity();
        activity1.setShelter(shelter.get());
        activity1.setCapacity(activity.getCapacity());
        activity1.setActivityType(activity.getActivityType());
        activityRepository.save(activity1);
    }
}
