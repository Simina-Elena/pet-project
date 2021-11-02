package com.acc.petproject.shelter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@AllArgsConstructor
public class ShelterServiceImpl implements ShelterService{

    private ShelterRepository shelterRepository;

    @Override
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    @Override
    public void addShelter(Shelter shelter) {
        shelter.setJoinedDate(LocalDate.now());
        shelterRepository.save(shelter);
    }

}
