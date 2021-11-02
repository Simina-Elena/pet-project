package com.acc.petproject.adoption;

import com.acc.petproject.pet.Pet;
import com.acc.petproject.pet.PetRepository;
import com.acc.petproject.shelter.Shelter;
import com.acc.petproject.shelter.ShelterRepository;
import com.acc.petproject.visitor.Visitor;
import com.acc.petproject.visitor.VisitorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdoptionServiceImpl implements AdoptionService{
    private AdoptionRepository adoptionRepository;
    private ShelterRepository shelterRepository;
    private PetRepository petRepository;
    private VisitorRepository visitorRepository;

    @Override
    public List<Adoption> getAllAdoptionsByShelterId(Long shelterId) {
        Shelter shelter = shelterRepository.getById(shelterId);
        return adoptionRepository.findAllByShelter(shelter);
    }

    @Override
    public void adopt(Adoption adoption, Long petId, Long shelterId, Long visitorId) {
        Optional<Shelter> shelter = shelterRepository.findById(shelterId);
        Optional<Pet> pet = petRepository.findById(petId);
        Optional<Visitor> visitor = visitorRepository.findById(visitorId);
        Adoption adoption1 = new Adoption();
        adoption1.setAdoptionStatus(AdoptionStatus.PENDING);
        adoption1.setPet(pet.get());
        adoption1.setShelter(shelter.get());
        adoption1.setVisitor(visitor.get());
        adoptionRepository.save(adoption1);
    }
}
