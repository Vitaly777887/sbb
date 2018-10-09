package org.chuzhinov.controller;

import lombok.var;
import org.chuzhinov.model.Passenger;
import org.chuzhinov.model.Station;
import org.chuzhinov.model.Train;
import org.chuzhinov.service.PassengerService;
import org.chuzhinov.service.StationService;
import org.chuzhinov.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @GetMapping(path = "passengers")
    public List<Passenger> timetable() {
        return passengerService.getAll();
    }

    @PostMapping(path = "stations")
    public void addStation(@RequestParam String stationName) {
        var station = new Station();
        station.setName(stationName);
        stationService.save(station);
    }

    @PostMapping(path = "trains")
    public void addTrain(@RequestParam int numberTrain, @RequestParam int countPassengers) {
        var train = new Train();
        train.setNumberTrain(numberTrain);
        train.setCountPassengers(countPassengers);
        trainService.save(train);
    }

    @GetMapping(path = "passengersontrain/{number}")
    public List<Passenger> getPassengersOnTrain(@PathVariable int number) {
        return trainService.getPassengersByNumberTrain(number);
    }

    @GetMapping(path = "trains")
    public List<Train> getTrains() {
        return trainService.getAll();
    }
}
