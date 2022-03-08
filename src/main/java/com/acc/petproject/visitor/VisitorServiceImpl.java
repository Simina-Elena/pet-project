package com.acc.petproject.visitor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VisitorServiceImpl implements VisitorService{

    private VisitorRepository visitorRepository;

    @Override
    public Visitor findVisitorByUsername(String username) {
        return visitorRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Could not find user with username: " + username));
    }

    @Override
    public Visitor updateVisitorInfo(String username, Visitor updatedVisitor) {
        Optional<Visitor> currentVisitor = visitorRepository.findByUsername(username);
        if(currentVisitor.isPresent()) {
            currentVisitor.get().setUsername(updatedVisitor.getUsername());
            currentVisitor.get().setEmail(updatedVisitor.getEmail());
            currentVisitor.get().setAddress(updatedVisitor.getAddress());
            currentVisitor.get().setAdoptions(currentVisitor.get().getAdoptions());
            currentVisitor.get().setRoles(currentVisitor.get().getRoles());
            currentVisitor.get().setDonations(currentVisitor.get().getDonations());
            currentVisitor.get().setPassword(currentVisitor.get().getPassword());
            currentVisitor.get().setFirstName(updatedVisitor.getFirstName());
            currentVisitor.get().setLastName(updatedVisitor.getLastName());
            currentVisitor.get().setReviews(currentVisitor.get().getReviews());
            currentVisitor.get().setBirthDate(updatedVisitor.getBirthDate());
            currentVisitor.get().setGender(updatedVisitor.getGender());
            currentVisitor.get().setPhoneNumber(updatedVisitor.getPhoneNumber());
            visitorRepository.save(currentVisitor.get());
        }
        return currentVisitor.get();
    }
}
