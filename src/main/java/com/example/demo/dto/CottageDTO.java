package com.example.demo.dto;

import com.example.demo.model.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CottageDTO {
    private Integer id;
    private String name;
    private String description;
    private AddressDTO address;
    private List<RoomDTO> rooms;
    private BigDecimal price;
    private List<String> rules;
    private String additionalInfo;
    private Integer ownerId;
    private List<String> imagePaths;
    private LocalDateTime availableStart;
    private LocalDateTime availableEnd;
    private Double rate;
    private Boolean editable;
    private List<ReviewDTO> reviews;

    public CottageDTO() {
    }

    public CottageDTO(Integer id, String name, String description, AddressDTO address, List<RoomDTO> rooms, BigDecimal price, List<String> rules, String additionalInfo, Integer ownerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.rooms = rooms;
        this.price = price;
        this.rules = rules;
        this.additionalInfo = additionalInfo;
        this.ownerId = ownerId;
    }

    public CottageDTO(Cottage cottage) {
        this.id = cottage.getId();
        this.name = cottage.getName();
        this.description = cottage.getDescription();
        this.address = new AddressDTO(cottage.getAddress());
        this.rooms = new ArrayList<>();
        for (Room room : cottage.getRooms()) {
            rooms.add(new RoomDTO(room));
        }
        this.price = cottage.getPriceList();
        this.rules = new ArrayList<>();
        for (Rule rule : cottage.getRules()) {
            rules.add(rule.getText());
        }
        this.additionalInfo = cottage.getAdditionalInfo();
        this.ownerId = cottage.getOwner().getId();
        this.imagePaths = new ArrayList<>();
        for (Image img : cottage.getImages()) {
            imagePaths.add(img.getPath());
        }
        List<Availability> avs = cottage.getAvailabilities();
        if (avs != null && avs.size() > 0) {
            Availability av = cottage.getAvailabilities().get(avs.size() - 1);
            this.availableStart = av.getStart();
            this.availableEnd = av.getEnd();
        }
        this.rate = cottage.getRateOrNegativeOne();
        this.editable = !cottage.hasFutureReservations();
        this.reviews = new ArrayList<>();
        for (Feedback fb : cottage.getReviews()) {
            this.reviews.add(new ReviewDTO(fb));
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<RoomDTO> getRooms() {
        if (rooms == null) rooms = new ArrayList<>();
        return rooms;
    }

    public void setRooms(List<RoomDTO> rooms) {
        this.rooms = rooms;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getRules() {
        if (rules == null) rules = new ArrayList<>();
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getImagePaths() {
        if (imagePaths == null) imagePaths = new ArrayList<>();
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }

    public Integer getNumberOfBeds() {
        return rooms.stream().map(RoomDTO::getNumberOfBeds).reduce(0, Integer::sum);
    }

    public LocalDateTime getAvailableStart() {
        return availableStart;
    }

    public void setAvailableStart(LocalDateTime availableStart) {
        this.availableStart = availableStart;
    }

    public LocalDateTime getAvailableEnd() {
        return availableEnd;
    }

    public void setAvailableEnd(LocalDateTime availableEnd) {
        this.availableEnd = availableEnd;
    }

    public Double getRate() {
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.valueOf(df.format(rate));
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
    }

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }
}

