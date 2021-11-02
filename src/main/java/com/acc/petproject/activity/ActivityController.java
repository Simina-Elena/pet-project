package com.acc.petproject.activity;

import com.acc.petproject.pet.Pet;
import com.acc.petproject.shelter.Shelter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/activity")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class ActivityController {
    private ActivityService activityService;

    @GetMapping("/list")
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @PostMapping(value = "/add/{shelterId}")
    public void addActivity(@RequestBody Activity activity, @PathVariable(value="shelterId") Long shelterId) {
        activityService.addActivity(activity, shelterId);
    }

}
