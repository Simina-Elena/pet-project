package com.acc.petproject.pet;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/pet")
@CrossOrigin
public class PetController {

    private final PetService petService;

    @GetMapping(value = "/list/{shelterId}")
    public List<Pet> getAllPetsByShelterId(@PathVariable(value = "shelterId") Long shelterId) {
        return petService.getAllPetsByShelterId(shelterId);
    }

    @PostMapping(value = "/add/{shelterId}")
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet, @PathVariable(value="shelterId") Long shelterId) {
        Pet newPet = petService.addPet(pet, shelterId);
        return new ResponseEntity<>(newPet, HttpStatus.OK);
    }

}
