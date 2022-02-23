package com.acc.petproject.pet;

import com.acc.petproject.shelter.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query(value = "SELECT * FROM pet WHERE shelter_id=?1 AND is_adopted=false", nativeQuery = true)
    List<Pet> findAllByShelterIdAndAdoptedIsFalse(Long shelterId);
}
