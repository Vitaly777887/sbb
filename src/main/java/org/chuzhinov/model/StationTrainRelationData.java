package org.chuzhinov.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.chuzhinov.utils.DateTimeUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class StationTrainRelationData implements Serializable {

    @DateTimeFormat(pattern = DateTimeUtils.DATETIMEPATTERN)
    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @DateTimeFormat(pattern = DateTimeUtils.DATETIMEPATTERN)
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
}
