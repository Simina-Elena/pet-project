package com.acc.petproject.pet;

import com.acc.petproject.shelter.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    List<Pet> findAllByShelter(Shelter shelter);
}
