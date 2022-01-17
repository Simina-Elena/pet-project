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
        return activityRepository.getAllByShelterId(shelterId);
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
}
