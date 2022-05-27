package com.example.demo.dto;

import java.time.LocalDateTime;

public class DatesDTO {
    private LocalDateTime start;
    private LocalDateTime end;

    public DatesDTO() {
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
