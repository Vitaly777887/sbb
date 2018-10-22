package org.chuzhinov.service;

import org.chuzhinov.model.Passenger;
import org.chuzhinov.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> getAll() {
        return passengerRepository.findAll();
    }

    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }
}
