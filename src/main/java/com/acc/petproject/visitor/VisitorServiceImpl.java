package com.acc.petproject.visitor;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VisitorServiceImpl implements VisitorService{

    private VisitorRepository visitorRepository;

    @Override
    public Visitor findVisitorByUsername(String username) {
        return visitorRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Could not find user with username: " + username));
    }
}
