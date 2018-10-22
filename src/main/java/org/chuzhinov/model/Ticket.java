package org.chuzhinov.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ticket", uniqueConstraints = {@UniqueConstraint(columnNames = {"train_id", "passenger_id"}, name = "tickets_unique_idx")})
@NoArgsConstructor
@RequiredArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @NonNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;
}
