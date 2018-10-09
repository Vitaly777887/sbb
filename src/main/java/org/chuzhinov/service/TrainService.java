package org.chuzhinov.service;

import org.chuzhinov.model.Passenger;
import org.chuzhinov.model.Station;
import org.chuzhinov.model.Ticket;
import org.chuzhinov.model.Train;
import org.chuzhinov.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;
    @Autowired
    private StationService stationService;

    public void save(Train train) {
        trainRepository.save(train);
    }

    public Train getByNumberTrain(Integer numberTrain) {
        return trainRepository.getByNumberTrain(numberTrain);
    }

    public List<Passenger> getPassengersByNumberTrain(Integer numberTrain) {
        return getByNumberTrain(numberTrain).getTickets()
                .stream().map(Ticket::getPassenger)
                .collect(Collectors.toList());
    }

    public List<Train> getAll() {
        return trainRepository.findAll();
    }

    public boolean isAvailable(String name, String surname, LocalDate birthday,
                               int numberTrain, String stationName) {
        Train train = getByNumberTrain(numberTrain);

        if (train.getTickets().size() < train.getCountPassengers()) {
            Passenger passenger = new Passenger(name, surname, birthday);

            if (!train.getTickets()
                    .stream()
                    .map(Ticket::getPassenger)
                    .collect(Collectors.toList())
                    .contains(passenger)) {
                Station station = stationService.getByName(stationName);

                if (train.getTimetable()
                        .get(station)
                        .getArrivalTime()
                        .isAfter(LocalDateTime.now().plusMinutes(10))) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Train> searchTrain(String stationNameFrom, String stationNameTo,
                                   LocalDateTime timeFrom, LocalDateTime timeTo) {
        Station stationFrom = stationService.getByName(stationNameFrom);
        Station stationTo = stationService.getByName(stationNameTo);
        return trainRepository.findAll().stream()
                .filter(t -> {
                    Set<Station> set = t.getTimetable().keySet();
                    return set.contains(stationFrom)
                            && set.contains(stationTo);
                })
                .filter(t ->
                        t.getTimetable().get(stationFrom).getDepartureTime()
                                .isBefore(t.getTimetable().get(stationTo).getArrivalTime())
                                && t.getTimetable().get(stationFrom).getDepartureTime().isAfter(timeFrom)
                                && t.getTimetable().get(stationFrom).getDepartureTime().isBefore(timeTo))
                .collect(Collectors.toList());
    }
}
