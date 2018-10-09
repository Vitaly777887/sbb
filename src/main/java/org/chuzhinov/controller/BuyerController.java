package org.chuzhinov.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.chuzhinov.model.StationTrainRelationData;
import org.chuzhinov.model.Train;
import org.chuzhinov.service.StationService;
import org.chuzhinov.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/buyer")
public class BuyerController {

    @Autowired
    private StationService stationService;
    @Autowired
    private TrainService trainService;

    //возвращает toString а не JSON
    @GetMapping(path = "timetable/{stationName}")
    public String getTimetable(@PathVariable String stationName) throws JsonProcessingException {
        Map<Train, StationTrainRelationData> timetable = stationService.getTimetable(stationName);

        return new ObjectMapper().writeValueAsString(timetable);
    }

    @PostMapping(path = "tickets")
    public void buyTicket(@RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam LocalDate birthday,
                          @RequestParam int numberTrain,
                          @RequestParam String stationName) {
        if (trainService.isAvailable(name, surname, birthday, numberTrain, stationName)) {


        }
    }

    @PostMapping(path = "trains/search")
    public List<Train> searchTrain(@RequestParam String stationNameFrom,
                                   @RequestParam String stationNameTo,
                                   @RequestParam String timeFrom,
                                   @RequestParam String timeTo) {
        LocalDateTime from = LocalDateTime.parse(timeFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime to = LocalDateTime.parse(timeTo, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        if (from.isBefore(to)) {
            return trainService.searchTrain(stationNameFrom, stationNameTo, from, to);
        }
        return new ArrayList<>();
    }
}

