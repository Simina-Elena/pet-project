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
    public Adoption adopt(AdoptionDto adoptionDto) {
        Optional<Pet> pet = petRepository.findById(adoptionDto.getPetId());
        Optional<Shelter> shelter = shelterRepository.findById(pet.get().getShelter().getId());
        Optional<Visitor> visitor = visitorRepository.findById(adoptionDto.getVisitorId());
        if(!adoptionRepository.existsByPetId(pet.get().getId())){
            Adoption adoption = new Adoption();
            adoption.setAdoptionStatus(AdoptionStatus.PENDING);
            adoption.setPet(pet.get());
            adoption.setShelter(shelter.get());
            adoption.setVisitor(visitor.get());
            adoptionRepository.save(adoption);
            return adoption;
        }
        return null;
    }

    @Override
    public Adoption acceptAdoption(Long adoptionId) {
        Optional<Adoption> adoption = adoptionRepository.findById(adoptionId);
        if(adoption.isPresent()) {
            adoption.get().setAdoptionStatus(AdoptionStatus.ACCEPTED);
            adoption.get().setShelter(adoption.get().getShelter());
            adoption.get().setVisitor(adoption.get().getVisitor());
            adoption.get().setPet(adoption.get().getPet());
            petRepository.findById(adoption.get().getPet().getId()).get().setAdopted(true);
            visitorRepository.findByUsername(adoption.get().getVisitor().getUsername()).get().addAdoption(adoption.get());
            System.out.println(visitorRepository.findByUsername(adoption.get().getVisitor().getUsername()).get().getAdoptions().toString());
            adoptionRepository.save(adoption.get());
        }
        return adoption.get();
    }
}
