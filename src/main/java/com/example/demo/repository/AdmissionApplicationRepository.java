package com.example.demo.repository;

import com.example.demo.model.AdmissionApplication;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AdmissionApplicationRepository extends JpaRepository<AdmissionApplication, UUID>, JpaSpecificationExecutor<AdmissionApplication> {

    @Query("SELECT a FROM AdmissionApplication a WHERE a.deleted = false "
            + "AND (:email IS NULL OR lower(a.email) = lower(:email)) "
            + "AND (:program IS NULL OR a.programApplied = :program)")
    Page<AdmissionApplication> search(@Param("email") String email, @Param("program") AdmissionApplication.Program program, Pageable pageable);

    Optional<Object> findByEmailAndDeletedFalse(String email);
}

