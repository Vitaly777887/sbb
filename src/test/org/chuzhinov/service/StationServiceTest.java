package org.chuzhinov.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class StationServiceTest {

    @Autowired
    private StationService stationService;
    @Test
    public void getByName() {
        assertEquals(stationService.getByName("voronezh").getId(),
               1);
    }
}