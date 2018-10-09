package org.chuzhinov.service;

import org.chuzhinov.model.Station;
import org.chuzhinov.model.StationTrainRelationData;
import org.chuzhinov.model.Train;
import org.chuzhinov.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    public void save(Station station) {
        stationRepository.save(station);
    }

    public Station getByName(String nsme) {
        return stationRepository.getStationByName(nsme);
    }

    public Map<Train, StationTrainRelationData> getTimetable(String name) {
        return getByName(name).getTimetable();
    }
}
