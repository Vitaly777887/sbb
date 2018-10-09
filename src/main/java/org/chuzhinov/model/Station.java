package org.chuzhinov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@Entity
@Data
@EqualsAndHashCode(exclude = {"timetable", "id"})
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @ToString.Exclude
    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "station_train",
            joinColumns = @JoinColumn(name = "station_id"))
    @MapKeyJoinColumn(name = "train_id")
    private Map<Train, StationTrainRelationData> timetable;
}
