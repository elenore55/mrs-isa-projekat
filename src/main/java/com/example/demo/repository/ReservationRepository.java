package com.example.demo.repository;

import com.example.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Modifying
    @Query("update Reservation r set r.reservationStatus='CANCELLED' where r.id = ?1")
    public void cancelReservation(Integer id);

    @Query("select r from Reservation r where r.id = :id")
    Reservation findOneById(@Param("id") Integer id);

    @Query("select r from Reservation r where r.offer.id = ?1")
    public List<Reservation> getByOfferId(Integer i);


}
