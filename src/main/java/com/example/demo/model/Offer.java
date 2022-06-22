package com.example.demo.model;

import com.example.demo.model.enums.AdminApprovalStatus;
import com.example.demo.model.enums.ReservationStatus;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "offer_id")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    protected String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    protected Address address;

    @Column
    protected String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<PriceList> priceHistory = new ArrayList<>();

    @Column
    protected BigDecimal priceList;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Rule> rules = new ArrayList<>();

    @Column
    protected String additionalInfo;

    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Availability> availabilities;

    @ManyToMany(mappedBy = "subscriptions", fetch = FetchType.LAZY)
    protected List<Client> subscribers = new ArrayList<>();

    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected List<Reservation> reservations = new ArrayList<>();

    @Version
    private Integer version;

    private Integer numberOfReservations;

    private Integer numberOfFastReservations;

    private Integer numberOfPriceLists;

    public Offer() {
    }

    public Boolean hasFutureReservations() {
        for (Reservation r : reservations) {
            if (r.getEnd().compareTo(LocalDateTime.now()) >= 0 && r.getReservationStatus() != ReservationStatus.CANCELLED) {
                return true;
            }
        }
        return false;
    }

    public List<Feedback> getReviews() {
        List<Feedback> reviews = new ArrayList<>();
        for (Reservation r : reservations) {
            Feedback fb = r.getFeedback();
            if (fb != null && fb.getStatus() == AdminApprovalStatus.APPROVED) reviews.add(r.getFeedback());
        }
        return reviews;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPriceList() {
        return priceList;
    }

    public void setPriceList(BigDecimal priceList) {
        this.priceList = priceList;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(List<Availability> availabilities) {
        this.availabilities = availabilities;
    }

    public List<Client> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Client> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
        if (reservations == null) this.numberOfReservations = 0;
        else this.numberOfReservations = reservations.size();
    }

    public List<PriceList> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(List<PriceList> priceHistory) {
        this.priceHistory = priceHistory;
        if (priceHistory == null) this.numberOfPriceLists = 0;
        else this.numberOfPriceLists = priceHistory.size();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Double getRateOrNegativeOne() {
        double sum = 0;
        int n = 0;
        for (Reservation r : getReservations()) {
            if (r.getFeedback() != null) {
                sum += r.getFeedback().getRating();
                n++;
            }
        }
        if (n == 0) return -1.0;
        return sum / n;
    }

    public Integer getNumberOfReservations() {
        return numberOfReservations;
    }

    public void setNumberOfReservations(Integer numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }

    public Integer getNumberOfPriceLists() {
        return numberOfPriceLists;
    }

    public void setNumberOfPriceLists(Integer numberOfPriceLists) {
        this.numberOfPriceLists = numberOfPriceLists;
    }

    public void incNumberOfReservations() {
        if (this.numberOfReservations == null) this.numberOfReservations = 0;
        this.numberOfReservations++;
    }

    public Integer getNumberOfFastReservations() {
        return numberOfFastReservations;
    }

    public void setNumberOfFastReservations(Integer numberOfFastReservations) {
        this.numberOfFastReservations = numberOfFastReservations;
    }

    public void incNumberOfFastReservations() {
        if (this.numberOfFastReservations == null) this.numberOfFastReservations = 0;
        this.numberOfFastReservations++;
    }

    public void incNumberOfPricelists() {
        if (this.numberOfPriceLists == null) this.numberOfPriceLists = 0;
        this.numberOfPriceLists++;
    }

    public void addReservation(Reservation r)
    {
        this.reservations.add(r);
        this.numberOfReservations = this.reservations.size();
    }

}
