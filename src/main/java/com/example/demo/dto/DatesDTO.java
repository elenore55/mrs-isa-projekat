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
        if (start == null) start = LocalDateTime.MIN;
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        if (end == null) end = LocalDateTime.now();
        this.end = end;
    }
}
