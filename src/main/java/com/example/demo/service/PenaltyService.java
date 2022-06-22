package com.example.demo.service;

import com.example.demo.model.Penalty;
import com.example.demo.repository.PenaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PenaltyService {
    private PenaltyRepository penaltyRepository;

    @Autowired
    public PenaltyService(PenaltyRepository penaltyRepository) {
        this.penaltyRepository = penaltyRepository;
    }

    public Penalty findOne(Integer id) {
        return penaltyRepository.findById(id).orElseGet(null);
    }
    public List<Penalty> findAll() {
        return penaltyRepository.findAll();
    }

    public Penalty save(Penalty penalty) {
        return penaltyRepository.save(penalty);
    }

    public void remove(Integer id) {
        penaltyRepository.deleteById(id);
    }

}
