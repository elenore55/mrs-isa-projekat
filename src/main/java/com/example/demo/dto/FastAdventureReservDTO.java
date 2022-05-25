package com.example.demo.dto;
        import com.example.demo.model.Adventure;
        import com.example.demo.model.FastAdventureReservation;
        import com.example.demo.model.FastReservation;
        import java.math.BigDecimal;
        import java.time.LocalDateTime;

public class FastAdventureReservDTO {
    private Integer id;
    private LocalDateTime start;
    private Integer duration;
    private BigDecimal price;
    private Integer maxPeople;
    private Integer adventure_id;

    public FastAdventureReservDTO() {
    }

    public FastAdventureReservDTO(FastAdventureReservation far) {
        this.id = far.getId();
        this.start = far.getStart();
        this.duration = far.getDuration();
        this.price = far.getPrice();
        this.maxPeople = far.getMaxPeople();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(Integer maxPeople) {
        this.maxPeople = maxPeople;
    }

    public Integer getAdventure_id() {
        return adventure_id;
    }

    public void setAdventure_id(Integer adventure_id) {
        this.adventure_id = adventure_id;
    }
}