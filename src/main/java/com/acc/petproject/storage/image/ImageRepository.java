package com.acc.petproject.storage.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> getAllByShelterId(Long shelterId);
    List<Image> getAllByPetId(Long petId);
    void deleteByName(String name);
}
