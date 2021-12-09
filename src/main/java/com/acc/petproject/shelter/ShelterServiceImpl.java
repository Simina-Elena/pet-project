package com.acc.petproject.shelter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ShelterServiceImpl implements ShelterService {

    private ShelterRepository shelterRepository;

    @Override
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    @Override
    public Shelter findShelterByUsername(String username) {
        return shelterRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Could not find user with username: " + username));
    }


}



