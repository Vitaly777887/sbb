package org.chuzhinov.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ticket", uniqueConstraints = {@UniqueConstraint(columnNames = {"train_id", "passenger_id"}, name = "tickets_unique_idx")})
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "train_id", nullable = false)
    private Train train;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;
}
