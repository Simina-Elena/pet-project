package com.acc.petproject.visitor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/visitors")
@AllArgsConstructor
@CrossOrigin
@Slf4j
public class VisitorController {

    private VisitorService visitorService;

    @GetMapping(path="/profile")
    public ResponseEntity<Visitor> getVisitorProfile(@RequestParam(value= "username") String username) {
        return new ResponseEntity<>(visitorService.findVisitorByUsername(username), HttpStatus.OK);
    }
}
