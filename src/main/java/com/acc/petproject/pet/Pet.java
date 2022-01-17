package com.acc.petproject.pet;

import com.acc.petproject.shelter.Shelter;
import com.acc.petproject.visitor.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Pet {
    @Id
    @SequenceGenerator(name = "pet_seq", sequenceName = "pet_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
    private Long id;
    private String name;
    private String pictureLink;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String race;
    private String age;
    private String color;
    private String description;
    private LocalDate joinedDate;
    @ManyToOne
    @JoinColumn(name = "shelter_id")
    @JsonIgnore
    private Shelter shelter;
    private boolean isAdopted;


}
