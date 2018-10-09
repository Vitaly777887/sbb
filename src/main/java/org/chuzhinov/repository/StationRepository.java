package org.chuzhinov.repository;

import org.chuzhinov.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Station getStationByName(String name);
}
