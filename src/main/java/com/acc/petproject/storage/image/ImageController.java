package com.acc.petproject.storage.image;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/images")
@CrossOrigin
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/for-shelter/{shelterId}")
    public List<Image> getAllImagesForShelter(@PathVariable(value = "shelterId") Long shelterId) {
        return imageService.getAllByShelterId(shelterId);
    }

    @GetMapping("/for-pet/{petId}")
    public List<Image> getAllImagesForPet(@PathVariable(value = "petId") Long petId) {
        return imageService.getAllByPetId(petId);
    }
}
