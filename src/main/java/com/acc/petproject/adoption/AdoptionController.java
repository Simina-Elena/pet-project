package com.acc.petproject.adoption;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/adoption")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class AdoptionController {
    private AdoptionService adoptionService;

    @GetMapping("/list/{shelterId}")
    public List<Adoption> getAllAdoptionsByShelterId(@PathVariable(value = "shelterId") Long shelterId) {
        return adoptionService.getAllAdoptionsByShelterId(shelterId);
    }
}
