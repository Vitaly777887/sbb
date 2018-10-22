package org.chuzhinov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(name = "train", uniqueConstraints = {@UniqueConstraint(columnNames = "number_train")})
@RequiredArgsConstructor
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(name = "number_train", nullable = false)
    private int numberTrain;

    @NonNull
    @Column(name = "count_passengers", nullable = false)
    private int countPassengers;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "train", fetch = FetchType.EAGER)
    private List<Ticket> tickets = new ArrayList<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "station_train",
            joinColumns = @JoinColumn(name = "train_id"))
    @MapKeyJoinColumn(name = "station_id")
    private Map<Station, StationTrainRelationData> timetable = new HashMap<>();
}
