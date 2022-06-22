package com.example.demo.repository;

import com.example.demo.model.Complaint;
import com.example.demo.model.ProfileData;
import com.example.demo.model.enums.AdminApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {
    @Transactional
    @Modifying
    @Query("update Complaint c set c.status = ?2 where c.id =?1")
    Integer updateStatus(Integer id, AdminApprovalStatus status);
}
