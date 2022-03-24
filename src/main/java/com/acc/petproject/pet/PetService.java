package com.acc.petproject.pet;

import java.util.List;

public interface PetService {

    List<Pet> getAllPetsUnadoptedByShelterId(Long shelterId);
    List<Pet> getAllPets(Long shelterId);
    Pet addPet(Pet pet, Long shelterId);
    Pet editPet(Long petId, PetDto petDto);
    void deletePet(Long petId);
    Pet getPetByPetId(Long petId);
}
