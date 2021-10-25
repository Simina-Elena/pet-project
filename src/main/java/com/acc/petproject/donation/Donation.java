package com.acc.petproject.donation;

import com.acc.petproject.shelter.Shelter;
import com.acc.petproject.visitor.Visitor;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Donation {
    @Id
    @SequenceGenerator(name = "donation_seq", sequenceName = "donation_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "donation_seq")
    private Long id;
    @ManyToOne
    private Visitor visitor;
    private BigDecimal amount;
    @ManyToOne
    private Shelter shelter;
}
