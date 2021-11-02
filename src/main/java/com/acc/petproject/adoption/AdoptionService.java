package com.acc.petproject.adoption;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdoptionService {

    List<Adoption> getAllAdoptionsByShelterId(Long shelterId);
    void adopt(Adoption adoption, Long petId, Long shelterId, Long visitorId);
}
