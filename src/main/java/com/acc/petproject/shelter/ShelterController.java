package com.acc.petproject.shelter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/shelter")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class ShelterController {
    private ShelterService shelterService;

    @GetMapping("/list")
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    @PostMapping("/add")
    public void addShelter(@RequestBody Shelter shelter) {
        shelterService.addShelter(shelter);
    }

}
