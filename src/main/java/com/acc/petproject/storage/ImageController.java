package com.acc.petproject.storage;

import com.acc.petproject.activity.Activity;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/images")
@CrossOrigin
public class ImageController {

    private final ImageRepository imageRepository;

    @GetMapping()
    public List<Image> getAllImages(@RequestParam(value = "shelterId") Long shelterId) {
        return imageRepository.getAllByShelterId(shelterId);
    }
}
