package org.chuzhinov.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.chuzhinov.utils.DateTimeUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// у пассажира может быть много билетов главное чтобы время не накладывалось, как добавить уникальность даты
@Entity
@Data
@Table(name = "passenger", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "surname"/*, "birthday"*/}, name = "passengers_unique_idx")})
@RequiredArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @NotBlank
    @Size(max = 20)
    @Column(nullable = false)
    private String name;

    @NonNull
    @NotBlank
    @Size(max = 20)
    @Column(nullable = false)
    private String surname;

    @NonNull
    @Past
    @DateTimeFormat(pattern = DateTimeUtils.DATEPATTERN)
    @Column(nullable = false)
    private LocalDate birthday;


    @JsonIgnore
    @OneToMany(mappedBy = "passenger")
    private List<Ticket> tickets = new ArrayList<>();
}
