package com.acc.petproject.pet;

import com.acc.petproject.shelter.Shelter;
import com.acc.petproject.shelter.ShelterRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PetServiceImpl implements PetService{

    private final PetRepository petRepository;
    private final ShelterRepository shelterRepository;

    @Override
    public List<Pet> getAllPetsByShelterId(Long shelterId) {
        Shelter shelter = shelterRepository.getById(shelterId);
        return petRepository.findAllByShelter(shelter);
    }

    @Override
    public Pet addPet(Pet pet, Long shelterId) {
        Optional<Shelter> shelter = shelterRepository.findById(shelterId);
        Pet pet1 = new Pet();
        pet1.setName(pet.getName());
        pet1.setAge(pet.getAge());
        pet1.setColor(pet.getColor());
        pet1.setRace(pet.getRace());
        pet1.setShelter(shelter.get());
        pet1.setAdopted(false);
        pet1.setDescription(pet.getDescription());
        pet1.setPictureLink(pet.getPictureLink());
        pet1.setJoinedDate(LocalDate.now());
        pet1.setGender(pet.getGender());
        petRepository.save(pet1);
        return pet1;
    }

    @Override
    public Pet editPet(Long petId, PetDto petDto) {
        Pet petToUpdate = petRepository.findById(petId).get();

        petToUpdate.setName(petDto.getName());
        petToUpdate.setGender(petDto.getGender());
        petToUpdate.setDescription(petDto.getDescription());
        petToUpdate.setAdopted(petDto.isAdopted());
        petToUpdate.setColor(petDto.getColor());
        petToUpdate.setAge(petDto.getAge());
        petToUpdate.setRace(petDto.getRace());
        petRepository.save(petToUpdate);

        return petToUpdate;
    }

    @Override
    public void deletePet(Long petId) {
        petRepository.delete(petRepository.findById(petId).get());
    }

    @Override
    public Pet getPetByPetId(Long petId) {
        return petRepository.findById(petId).get();
    }
}
