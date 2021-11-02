package com.acc.petproject.shelter;

import com.acc.petproject.activity.Activity;
import com.acc.petproject.adoption.Adoption;
import com.acc.petproject.donation.Donation;
import com.acc.petproject.pet.Pet;
import com.acc.petproject.review.Review;
import com.acc.petproject.visitor.Address;
import com.acc.petproject.visitor.Visitor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Shelter {
    @Id
    @SequenceGenerator(name = "shelter_seq", sequenceName = "shelter_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shelter_seq")
    private Long id;
    @Column(nullable = false)
    private String name;
    private Address address;
    @Column(length = 1000)
    private String pictureLink;
    @Column(nullable = false)
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate joinedDate = LocalDate.now();
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pet> pets;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Activity> activities;
    private double rating;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Review> reviews;
    @OneToMany(mappedBy = "shelter")
    private List<Donation> donations;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    private List<Adoption> adoptions;

}
