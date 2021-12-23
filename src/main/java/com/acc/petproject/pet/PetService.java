package com.acc.petproject.pet;

import com.acc.petproject.shelter.Shelter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PetService {

    List<Pet> getAllPetsByShelterId(Long shelterId);
    Pet addPet(Pet pet, Long shelterId);
    Pet editPet(Long petId, PetDto petDto);
    void deletePet(Long petId);
    Pet getPetByPetId(Long petId);
}
