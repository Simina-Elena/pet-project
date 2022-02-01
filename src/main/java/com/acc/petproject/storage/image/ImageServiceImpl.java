package com.acc.petproject.storage.image;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService{

    private ImageRepository imageRepository;

    @Override
    public List<Image> getAllByShelterId(Long shelterId) {
        return imageRepository.getAllByShelterId(shelterId);
    }

    @Override
    public List<Image> getAllByPetId(Long petId) {
        return imageRepository.getAllByPetId(petId);
    }
}
