package com.acc.petproject.activity;

import com.acc.petproject.pet.Pet;
import com.acc.petproject.shelter.Shelter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/activities")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class ActivityController {
    private ActivityService activityService;

    @GetMapping()
    public List<Activity> getAllActivities(@RequestParam(value = "shelterId") Long shelterId) {
        return activityService.getAllActivitiesByShelterId(shelterId);
    }

    @PostMapping()
    public void addActivity(@RequestBody ActivityDto activityDto) {
        activityService.addActivity(activityDto);
    }
}
