package com.acc.petproject.activity;

import com.acc.petproject.reservation.Reservation;
import com.acc.petproject.shelter.Shelter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Activity {
    @Id
    @SequenceGenerator(name = "activity_seq", sequenceName = "activity_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_seq")
    private Long id;
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;
    private int capacity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shelter_id")
    @JsonIgnore
    private Shelter shelter;
    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public void increaseCapacity() {
        capacity++;
    }

    public void decreaseCapacity() {
        capacity--;
    }

}
