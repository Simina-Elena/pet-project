package com.acc.petproject.adoption;

import com.acc.petproject.shelter.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {

    List<Adoption> findAllByShelter(Shelter shelter);
}
