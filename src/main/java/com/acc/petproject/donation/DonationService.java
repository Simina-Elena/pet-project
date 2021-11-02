package com.acc.petproject.donation;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface DonationService {
    List<Donation> getAllDonationsByShelterId(Long shelterId);
}
