package com.example.demo.repository;

import com.example.demo.model.RegistrationRequest;
import com.example.demo.model.enums.AdminApprovalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RegistrationRequestRepository extends JpaRepository<RegistrationRequest, Integer> {
    @Transactional
    @Modifying
    @Query("update RegistrationRequest r set r.approvalStatus = ?2,r.profileData=null where r.id =?1")
    Integer updateStatus(Integer id, AdminApprovalStatus approval_status);
}
