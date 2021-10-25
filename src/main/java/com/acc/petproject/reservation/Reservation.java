package com.acc.petproject.reservation;

import com.acc.petproject.activity.Activity;
import com.acc.petproject.visitor.Visitor;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @SequenceGenerator(name = "reservation_seq", sequenceName = "reservation_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    private Long id;
    @ManyToOne
    private Visitor visitor;
    @ManyToOne
    private Activity activity;
    private LocalDate startDate;
    private LocalDate endDate;
    private final ReservationStatus reservationStatus = ReservationStatus.APPROVED;
}
