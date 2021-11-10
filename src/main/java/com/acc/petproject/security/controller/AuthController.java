package com.acc.petproject.security.controller;

import com.acc.petproject.security.jwt.JwtUtils;
import com.acc.petproject.security.model.Role;
import com.acc.petproject.security.model.RoleType;
import com.acc.petproject.security.payload.request.LoginRequest;
import com.acc.petproject.security.payload.request.SignUpRequest;
import com.acc.petproject.security.payload.response.JwtResponse;
import com.acc.petproject.security.payload.response.MessageResponse;
import com.acc.petproject.security.repository.RoleRepository;
import com.acc.petproject.security.services.UserDetailsImpl;
import com.acc.petproject.security.services.UserDetailsServiceImpl;
import com.acc.petproject.shelter.Shelter;
import com.acc.petproject.shelter.ShelterRepository;
import com.acc.petproject.visitor.Visitor;
import com.acc.petproject.visitor.VisitorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    AuthenticationManager authenticationManager;

    VisitorRepository visitorRepository;

    ShelterRepository shelterRepository;

    RoleRepository roleRepository;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;



    @PostMapping("/register/shelter")
    public ResponseEntity<?> registerShelter(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (shelterRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error, name is already taken"));
        }
        if (shelterRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error, email is already taken"));
        }

        Shelter shelter = new Shelter(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhoneNumber());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role shelterRole = roleRepository.findByName(RoleType.ROLE_SHELTER)
                    .orElseThrow(() -> new RuntimeException("error: Role is not found."));
            roles.add(shelterRole);
        }

        shelter.setRoles(roles);
        shelter.setJoinedDate(LocalDate.now());
        shelterRepository.save(shelter);
        return ResponseEntity.ok(new MessageResponse("Shelter registered successfully!"));
    }

    @PostMapping("/register/visitor")
    public ResponseEntity<?> registerVisitor(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (visitorRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error, name is already taken"));
        }
        if (visitorRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error, email is already taken"));
        }
        Visitor visitor = new Visitor(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()), signUpRequest.getPhoneNumber());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role shelterRole = roleRepository.findByName(RoleType.ROLE_VISITOR)
                    .orElseThrow(() -> new RuntimeException("error: Role is not found."));
            roles.add(shelterRole);
        }
        visitor.setRoles(roles);
        visitorRepository.save(visitor);
        return ResponseEntity.ok(new MessageResponse("Visitor registered successfully!"));

    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.error(loginRequest.getUsername());
        log.error(loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetailsImplementation =
                (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetailsImplementation.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String jwt = jwtUtils.generateJwtToken(loginRequest.getUsername(), roles);
        log.error(jwt);
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetailsImplementation.getId(),
                userDetailsImplementation.getUsername(),
                userDetailsImplementation.getEmail(),
                roles));

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }
}
