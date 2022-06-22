package com.example.demo.service;

import com.example.demo.model.Feedback;
import com.example.demo.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private FeedbackRepository repository;

    @Autowired
    public FeedbackService(FeedbackRepository repository)
    {
        this.repository = repository;
    }

    public Feedback save(Feedback f)
    {
        return this.repository.save(f);
    }
}
