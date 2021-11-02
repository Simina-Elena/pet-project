package com.acc.petproject.donation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DonationServiceImpl implements DonationService{

    private DonationRepository donationRepository;

    @Override
    public List<Donation> getAllDonationsByShelterId(Long shelterId) {
        return donationRepository.findAllByShelterId(shelterId);
    }
}
