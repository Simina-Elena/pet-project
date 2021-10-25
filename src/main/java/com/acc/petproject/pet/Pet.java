package com.acc.petproject.pet;

import com.acc.petproject.shelter.Shelter;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pet {
    @Id
    @SequenceGenerator(name = "pet_seq", sequenceName = "pet_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_seq")
    private Long id;
    private String name;
    private String pictureLink;
    private String gender;
    private String race;
    private String age;
    private String color;
    private String description;
    @ManyToOne
    private Shelter shelter;
    private boolean isAdopted;
}
