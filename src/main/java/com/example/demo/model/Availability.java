package com.example.demo.model;

import java.time.LocalDateTime;

public class Availability {
    private LocalDateTime start;
    private LocalDateTime end;

    public Availability() {
    }

    public Availability(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
