package org.chuzhinov.service;

import org.chuzhinov.model.Station;
import org.chuzhinov.repository.StationRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class StationServiceTest {

    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private StationService stationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getByName() {

        final Station testStation = new Station();
        testStation.setId(1);
        when(stationRepository.getStationByName(any(String.class))).thenReturn(testStation);

        assertEquals(stationService.getByName("voronezh").getId(),
                1);
    }
}