package com.acc.petproject.shelter;

import com.acc.petproject.adoption.Adoption;
import com.acc.petproject.adoption.AdoptionRepository;
import com.acc.petproject.adoption.AdoptionStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private ShelterRepository shelterRepository;
    private AdoptionRepository adoptionRepository;

    @Override
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    @Override
    public Shelter findShelterByUsername(String username) {
        return shelterRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Could not find user with username: " + username));
    }

    @Override
    public Shelter updateShelterInfo(String username, Shelter updatedShelter) {
        Optional<Shelter> currentShelter = shelterRepository.findByUsername(username);
        System.out.println(updatedShelter.getJoinedDate() + " joined date");
        if(currentShelter.isPresent()) {
            currentShelter.get().setUsername(updatedShelter.getUsername());
            currentShelter.get().setEmail(updatedShelter.getEmail());
            currentShelter.get().setAddress(updatedShelter.getAddress());
            currentShelter.get().setPets(currentShelter.get().getPets());
            currentShelter.get().setRoles(currentShelter.get().getRoles());
            currentShelter.get().setPassword(currentShelter.get().getPassword());
            currentShelter.get().setJoinedDate(updatedShelter.getJoinedDate());
            currentShelter.get().setActivities(currentShelter.get().getActivities());
            currentShelter.get().setAdoptions(currentShelter.get().getAdoptions());
            currentShelter.get().setDonations(currentShelter.get().getDonations());
            currentShelter.get().setPictures(currentShelter.get().getPictures());
            currentShelter.get().setPhoneNumber(updatedShelter.getPhoneNumber());
            currentShelter.get().setRating(currentShelter.get().getRating());
            currentShelter.get().setReviews(currentShelter.get().getReviews());
            shelterRepository.save(currentShelter.get());
        }
        return currentShelter.get();

    }


}



