package com.example.demo.repository;

import com.example.demo.model.Client;
import com.example.demo.model.Reservation;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Modifying
    @Query("update Reservation r set r.reservationStatus='CANCELLED' where r.id = ?1")
    public void cancelReservation(Integer id);

    @Query("select r from Reservation r where r.id = :id")
    Reservation findOneById(@Param("id") Integer id);

    @Query("select r from Reservation r where r.offer.id = ?1")
    public List<Reservation> getByOfferId(Integer i);


}
