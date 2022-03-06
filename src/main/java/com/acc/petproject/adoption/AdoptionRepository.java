package com.acc.petproject.adoption;

import com.acc.petproject.pet.Pet;
import com.acc.petproject.shelter.Shelter;
import com.acc.petproject.visitor.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {

    List<Adoption> findAllByShelter(Shelter shelter);
    Boolean existsByPetId(Long petId);
    @Query(value = "SELECT * FROM adoption WHERE visitor_id=?1 AND adoption_status='ACCEPTED'", nativeQuery = true)
    List<Adoption> findAllByVisitorAndAdoptionStatus_Accepted(Visitor visitor);
}
