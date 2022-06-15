package com.example.demo.repository;

import com.example.demo.model.Reservation;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Modifying
    @Query("update Reservation r set r.reservationStatus='CANCELLED' where r.id = ?1")
    public void cancelReservation(Integer id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select r from Reservation r where r.id = :id")
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="0")})
    public Reservation findOne(@Param("id")Integer id);
}
