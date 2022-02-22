package com.acc.petproject.shelter;

import com.acc.petproject.adoption.Adoption;
import com.acc.petproject.security.payload.response.MessageResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/shelter")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class ShelterController {
    private ShelterService shelterService;

    @GetMapping(path="/list")
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }

    @GetMapping(path="/profile/{username}")
    public ResponseEntity<Shelter> getShelterProfile(@PathVariable(value= "username") String username ) {
        return new ResponseEntity<>(shelterService.findShelterByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutShelter() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }

    @PatchMapping("/profile-update/{username}")
    public ResponseEntity<Shelter> updateShelterInfo(@PathVariable(value = "username") String username, @RequestBody Shelter updatedShelter) {
        return new ResponseEntity<>(shelterService.updateShelterInfo(username, updatedShelter), HttpStatus.OK);
    }
}
