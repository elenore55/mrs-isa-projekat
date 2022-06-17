package com.example.demo.service;

import com.example.demo.model.FastReservation;
import com.example.demo.repository.FastReservationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FastReservationService {
    private FastReservationRepo repository;

    @Autowired
    public FastReservationService(FastReservationRepo f)
    {
        this.repository = f;
    }

    public FastReservation save(FastReservation f) {return this.repository.save(f);}
}
