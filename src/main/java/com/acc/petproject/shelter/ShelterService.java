package com.acc.petproject.shelter;

import com.acc.petproject.adoption.Adoption;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ShelterService {
    List<Shelter> getAllShelters();
    Shelter findShelterByUsername(String username);
    Shelter updateShelterInfo(String username, Shelter updatedShelter);
}
