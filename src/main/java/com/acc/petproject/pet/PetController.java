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
        return petService.getAllPetsUnadoptedByShelterId(shelterId);
    }
    @GetMapping(value = "/list-all/{shelterId}")
    public List<Pet> getAllByShelterId(@PathVariable(value = "shelterId") Long shelterId) {
        return petService.getAllPets(shelterId);
    }

    @PostMapping(value = "/add/{shelterId}")
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet, @PathVariable(value="shelterId") Long shelterId) {
        Pet newPet = petService.addPet(pet, shelterId);
        return new ResponseEntity<>(newPet, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{petId}")
    public void deletePet(@PathVariable(value = "petId") Long petId) {
        petService.deletePet(petId);
    }

    @PatchMapping(value = "/edit/{petId}")
    public ResponseEntity<Pet> editPet(@PathVariable(value = "petId") Long petId, @RequestBody PetDto petDto) {
        Pet editedPet = petService.editPet(petId, petDto);
        return new ResponseEntity<>(editedPet, HttpStatus.OK);
    }

    @GetMapping(value = "/{petId}")
    public ResponseEntity<Pet> getPetByPetId(@PathVariable(value = "petId") Long petId) {
        return new ResponseEntity<>(petService.getPetByPetId(petId), HttpStatus.OK);
    }

}
