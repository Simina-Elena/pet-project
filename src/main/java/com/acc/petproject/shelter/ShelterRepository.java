package com.acc.petproject.shelter;

import com.acc.petproject.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    Optional<Shelter> findByUsername(String name);

    Boolean existsByUsername(String name);
    Boolean existsByEmail(String email);



}
