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
    public List<Activity> getAllActivitiesByShelterId(Long shelterId) {
        return activityRepository.getAllByShelterIdOrderById(shelterId);
    }

    @Override
    public void addActivity(ActivityDto activityDto) {
        Optional<Shelter> shelter = shelterRepository.findById(activityDto.getShelterId());
        Activity activity1 = new Activity();
        activity1.setShelter(shelter.get());
        activity1.setCapacity(activityDto.getCapacity());
        activity1.setActivityType(activityDto.getActivityType());
        activityRepository.save(activity1);
    }

    @Override
    public void deleteActivity(Long activityId) {
        activityRepository.delete(activityRepository.findById(activityId).get());
    }

    @Override
    public Activity increaseCapacity(Long activityId) {
        Activity activity = activityRepository.findById(activityId).get();
        activity.increaseCapacity();
        activityRepository.save(activity);
        return activity;
    }

    @Override
    public Activity decreaseCapacity(Long activityId) {
        Activity activity = activityRepository.findById(activityId).get();
        activity.decreaseCapacity();;
        activityRepository.save(activity);
        return activity;
    }

    @Override
    public Activity getActivityByActivityId(Long activityId) {
        return activityRepository.findById(activityId).get();
    }

}
