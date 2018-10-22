package org.chuzhinov.service;

import org.chuzhinov.model.*;
import org.chuzhinov.repository.TrainRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TrainServiceTest {

    @Mock
    private TrainRepository trainRepository;
    @InjectMocks
    private TrainService trainService;
    @Mock
    private StationService stationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isAvailablePassengerOnTrainNoPassenger() {
        String name = "name";
        String surname = "surname";
        LocalDate birthday = LocalDate.now().minusYears(20);
        int numberTrain = 1;
        String stationName = "city";

        Train trainTest = new Train(numberTrain, 20);

        Map<Station, StationTrainRelationData> timetable = new HashMap<>();
        Station station = new Station(stationName);
        StationTrainRelationData stationTrainRelationData = new StationTrainRelationData();
        stationTrainRelationData.setArrivalTime(LocalDateTime.now().plusMinutes(15));
        timetable.put(station, stationTrainRelationData);
        trainTest.setTimetable(timetable);

        when(trainRepository.getByNumberTrain(any(Integer.class))).thenReturn(trainTest);
        when(stationService.getByName(any(String.class))).thenReturn(station);

        assertTrue(trainService.isAvailablePassengerOnTrain(name, surname, birthday, numberTrain, stationName));
    }

    @Test
    public void isAvailablePassengerOnTrainHasThatPassenger() {
        String name = "name";
        String surname = "surname";
        LocalDate birthday = LocalDate.now().minusYears(20);
        int numberTrain = 1;
        String stationName = "city";

        Train trainTest = new Train(numberTrain, 20);

        Map<Station, StationTrainRelationData> timetable = new HashMap<>();
        Station station = new Station(stationName);
        StationTrainRelationData stationTrainRelationData = new StationTrainRelationData();
        stationTrainRelationData.setArrivalTime(LocalDateTime.now().plusMinutes(15));
        timetable.put(station, stationTrainRelationData);
        trainTest.setTimetable(timetable);

        Ticket ticket = new Ticket();
        ticket.setPassenger(new Passenger(name, surname, birthday));
        ArrayList<Ticket> arrayList = new ArrayList<>();
        arrayList.add(ticket);
        trainTest.setTickets(arrayList);

        when(trainRepository.getByNumberTrain(any(Integer.class))).thenReturn(trainTest);
        when(stationService.getByName(any(String.class))).thenReturn(station);

        assertFalse(trainService.isAvailablePassengerOnTrain(name, surname, birthday, numberTrain, stationName));
    }

    @Test
    public void searchTrainNotFound() {
        List<Train> trains = trainService.searchTrain("voronezh", "moscow", LocalDateTime.now().plusMinutes(1), LocalDateTime.now().plusMinutes(30));

        assertEquals(trains, new ArrayList<>());
    }

    @Test
    public void searchTrainFound() {
        Station stationFrom = new Station("voronezh");
        Station stationTo = new Station("moscow");
        Train train = new Train(123, 30);

        ArrayList<Train> trains = new ArrayList<>();
        trains.add(train);

        HashMap<Station, StationTrainRelationData> timetable = new HashMap<>();
        timetable.put(stationFrom, new StationTrainRelationData(null, LocalDateTime.now().plusMinutes(20)));
        timetable.put(stationTo, new StationTrainRelationData(LocalDateTime.now().plusMinutes(40), null));
        train.setTimetable(timetable);

        when(stationService.getByName(any(String.class))).thenReturn(stationFrom).thenReturn(stationTo);
        when(trainService.getAll()).thenReturn(trains);

        List<Train> trainsTest = trainService.searchTrain("voronezh", "moscow", LocalDateTime.now().plusMinutes(1), LocalDateTime.now().plusMinutes(50));
        assertEquals(trainsTest, trains);
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchTrainThrowException1() {
        trainService.searchTrain("voronezh", "moscow", LocalDateTime.now().minusMinutes(2), LocalDateTime.now());

    }

    @Test(expected = IllegalArgumentException.class)
    public void searchTrainThrowException2() {
        trainService.searchTrain("voronezh", "moscow", LocalDateTime.now().plusMinutes(10), LocalDateTime.now().plusMinutes(5));
    }

    @Test
    public void getPassengersByNumberTrain() {


    }
}