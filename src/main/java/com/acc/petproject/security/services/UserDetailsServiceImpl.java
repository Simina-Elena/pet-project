package com.acc.petproject.security.services;

import com.acc.petproject.shelter.Shelter;
import com.acc.petproject.shelter.ShelterRepository;
import com.acc.petproject.visitor.Visitor;
import com.acc.petproject.visitor.VisitorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    ShelterRepository shelterRepository;
    VisitorRepository visitorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Shelter> shelter = shelterRepository.findByUsername(username);
        Optional<Visitor> visitor = visitorRepository.findByUsername(username);
        if (visitor.isPresent()) {
            return UserDetailsImpl.build(visitor.get());
        } else if (shelter.isPresent()) {
            return UserDetailsImpl.buildShelter(shelter.get());
        }
        else {
            throw new UsernameNotFoundException("No shelter or visitor with username " + username);
        }
    }
}
