package com.acc.petproject.activity;

import com.acc.petproject.pet.Pet;
import com.acc.petproject.shelter.Shelter;
import jdk.jfr.Registered;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/activity")
    public Activity getActivityByActivityId(@RequestParam(value = "activityId") Long activityId) {
        return activityService.getActivityByActivityId(activityId);
    }

    @PostMapping()
    public void addActivity(@RequestBody ActivityDto activityDto) {
        activityService.addActivity(activityDto);
    }

    @DeleteMapping()
    public void deleteActivity(@RequestParam(value= "activityId") Long activityId) {
        activityService.deleteActivity(activityId);
    }

    @PatchMapping("/increase-capacity")
    public ResponseEntity<Activity> increaseCapacity(@RequestParam(value = "activityId") Long activityId) {
        return new ResponseEntity<>(activityService.increaseCapacity(activityId), HttpStatus.OK);
    }

    @PatchMapping("/decrease-capacity")
    public ResponseEntity<Activity> decreaseCapacity(@RequestParam(value = "activityId") Long activityId) {
        return new ResponseEntity<>(activityService.decreaseCapacity(activityId), HttpStatus.OK);
    }
}
