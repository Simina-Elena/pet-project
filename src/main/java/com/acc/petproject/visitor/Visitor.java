package com.acc.petproject.visitor;


import com.acc.petproject.adoption.Adoption;
import com.acc.petproject.donation.Donation;
import com.acc.petproject.review.Review;
import com.acc.petproject.security.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Entity
public class Visitor {
    @Id
    @SequenceGenerator(name = "visitor_seq", sequenceName = "visitor_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitor_seq")
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String birthDate;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name ="city", column = @Column(name = "city")),
            @AttributeOverride(name ="country", column = @Column(name = "country")),
            @AttributeOverride(name ="street", column = @Column(name = "street")),
            @AttributeOverride(name ="number", column = @Column(name = "number")),
            @AttributeOverride(name ="zip", column = @Column(name = "zip"))
    })
    private Address address;
    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Adoption> adoptions;
    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Donation> donations;
    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> reviews;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "visitor_roles",
            joinColumns = @JoinColumn(name = "visitor_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Visitor(String username, String email, String password, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void addAdoption(Adoption adoption) {
        adoptions.add(adoption);
    }
    public void removeAdoption(Adoption adoption) {adoptions.remove(adoption);}
}
