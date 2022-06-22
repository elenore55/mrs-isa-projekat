package com.example.demo.service;

import com.example.demo.model.Coefficients;
import com.example.demo.repository.CoefficientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoefficientsService {

    @Autowired
    private CoefficientsRepository coefficientsRepository;
    public Coefficients findOne(Integer id){ return coefficientsRepository.findById(id).orElse(null);}
    public List<Coefficients> findAll() {return coefficientsRepository.findAll();}
    public Coefficients save(Coefficients coefficients){
        return coefficientsRepository.save(coefficients);
    }

    public void remove(Integer id) { coefficientsRepository.deleteById(id);}

}
