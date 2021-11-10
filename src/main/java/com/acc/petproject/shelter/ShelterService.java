package com.acc.petproject.shelter;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ShelterService {
    List<Shelter> getAllShelters();

}
