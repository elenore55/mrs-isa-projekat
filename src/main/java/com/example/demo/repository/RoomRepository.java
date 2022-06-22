package com.example.demo.repository;

import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Modifying
    @Transactional
    @Query("delete from Room r where r.cottage.id =?1")
    void deleteRooms(Integer cottage_id);
}
