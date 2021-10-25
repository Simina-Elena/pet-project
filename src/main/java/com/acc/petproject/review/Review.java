package com.acc.petproject.review;

import com.acc.petproject.shelter.Shelter;
import com.acc.petproject.visitor.Visitor;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @SequenceGenerator(name = "review_seq", sequenceName = "review_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq")
    private Long id;
    private String title;
    private String description;
    @ManyToOne
    private Visitor visitor;
    @ManyToOne
    private Shelter shelter;
    private double rating;
    private LocalDateTime postedAt;
    private LocalDateTime editedAt;

}
