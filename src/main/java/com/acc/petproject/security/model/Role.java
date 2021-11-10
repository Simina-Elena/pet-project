package com.acc.petproject.security.model;

import lombok.*;

import javax.persistence.*;


@Table(name = "roles")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    @Id
    @SequenceGenerator(name = "roles_sequence", sequenceName = "roles_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_sequence")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleType name;
}
