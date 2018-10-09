package org.chuzhinov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name = "train", uniqueConstraints = {@UniqueConstraint(columnNames = "number_train")})
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number_train", nullable = false)
    private int numberTrain;

    @Column(name = "count_passengers", nullable = false)
    private int countPassengers;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "train", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "station_train",
            joinColumns = @JoinColumn(name = "train_id"))
    @MapKeyJoinColumn(name = "station_id")
    private Map<Station, StationTrainRelationData> timetable;
}
