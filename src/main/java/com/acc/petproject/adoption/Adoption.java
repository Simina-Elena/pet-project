package com.acc.petproject.adoption;

import com.acc.petproject.pet.Pet;
import com.acc.petproject.shelter.Shelter;
import com.acc.petproject.visitor.Visitor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Adoption {
    @Id
    @SequenceGenerator(name = "adoption_seq", sequenceName = "adoption_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adoption_seq")
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Visitor visitor;
    @OneToOne
    private Pet pet;
    private AdoptionStatus adoptionStatus;
    @ManyToOne
    @JsonIgnore
    private Shelter shelter;
}
