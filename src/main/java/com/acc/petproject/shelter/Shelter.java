package com.acc.petproject.shelter;

import com.acc.petproject.activity.Activity;
import com.acc.petproject.adoption.Adoption;
import com.acc.petproject.donation.Donation;
import com.acc.petproject.pet.Pet;
import com.acc.petproject.review.Review;
import com.acc.petproject.storage.image.Image;
import com.acc.petproject.visitor.Address;
import com.acc.petproject.security.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

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
    private String username;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name ="city", column = @Column(name = "city")),
            @AttributeOverride(name ="country", column = @Column(name = "country")),
            @AttributeOverride(name ="street", column = @Column(name = "street")),
            @AttributeOverride(name ="number", column = @Column(name = "number")),
            @AttributeOverride(name ="zip", column = @Column(name = "zip"))
    })
    private Address address;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Image> pictures;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phoneNumber;
    private LocalDate joinedDate = LocalDate.now();
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pet> pets;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Activity> activities;
    private double rating;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;
    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private List<Donation> donations;
    @OneToMany(mappedBy = "shelter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Adoption> adoptions;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "shelter_roles", joinColumns = @JoinColumn(name = "shelter_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Shelter(String name, String email, String password, String phoneNumber) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
