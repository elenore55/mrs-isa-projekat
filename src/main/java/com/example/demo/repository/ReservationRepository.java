package com.example.demo.repository;

import com.example.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Modifying
    @Query("update Reservation r set r.reservationStatus='CANCELLED' where r.id = ?1")
    public void cancelReservation(Integer id);
}
