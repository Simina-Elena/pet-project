package com.acc.petproject.storage.image;

import java.util.List;

public interface ImageService {
    List<Image> getAllByShelterId(Long shelterId);
    List<Image> getAllByPetId(Long petId);
}
