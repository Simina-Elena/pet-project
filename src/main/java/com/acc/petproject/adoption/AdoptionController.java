package com.acc.petproject.adoption;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/adoptions")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class AdoptionController {
    private AdoptionService adoptionService;

    @GetMapping("/byShelter")
    public List<Adoption> getAllAdoptionsByShelterId(@RequestParam(value = "shelterId") Long shelterId) {
        return adoptionService.getAllAdoptionsByShelterId(shelterId);
    }

    @GetMapping("/byVisitor")
    public List<Adoption> getAllAdoptionsByVisitorId(@RequestParam(value = "visitorId") Long visitorId) {
        System.out.println(adoptionService.getAllAdoptionsByVisitorId(visitorId));
        return adoptionService.getAllAdoptionsByVisitorId(visitorId);
    }

    @PostMapping()
    public ResponseEntity<Adoption> newAdoption(@RequestBody AdoptionDto adoptionDto) {
        Adoption adoption = adoptionService.adopt(adoptionDto);
        return new ResponseEntity<>(adoption, HttpStatus.OK);
    }

    @PatchMapping("/accept-adoption")
    public ResponseEntity<Adoption> acceptAdoption(@RequestParam(value = "adoptionId") Long adoptionId) {
        return new ResponseEntity<>(adoptionService.acceptAdoption(adoptionId), HttpStatus.OK);
    }

    @PatchMapping("/decline-adoption")
    public ResponseEntity<Adoption> declineAdoption(@RequestParam(value = "adoptionId") Long adoptionId) {
        return new ResponseEntity<>(adoptionService.declineAdoption(adoptionId), HttpStatus.OK);
    }
}
