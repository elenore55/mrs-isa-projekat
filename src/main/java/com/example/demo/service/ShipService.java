package com.example.demo.service;

import com.example.demo.model.Reservation;
import com.example.demo.model.Ship;
import com.example.demo.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShipService {
    private ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public Ship findOne(Integer id) {
        return this.shipRepository.findById(id).orElseGet(null);
    }

    public Ship save(Ship ship) {
        return this.shipRepository.save(ship);
    }

    public void remove(Integer id) {
        shipRepository.deleteById(id);
    }

    public boolean checkReservations(Ship ship) {
        for (Reservation r : ship.getReservations()) {
            if (r.getEnd().compareTo(LocalDateTime.now()) >= 0) {
                return false;
            }
        }
        return true;
    }

    public List<Ship> getShips() {
        return shipRepository.findAll();
    }
}
