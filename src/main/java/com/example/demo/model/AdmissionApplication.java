package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "applications")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AdmissionApplication {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "applicant_name", nullable = false)
    private String applicantName;

    @Column(nullable = false)
    private String email;

    private String phone;

    @Column(name = "current_step", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStep currentStep = ApplicationStep.STARTED;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status = ApplicationStatus.PENDING;

    @Column(name = "program_applied")
    @Enumerated(EnumType.STRING)
    private Program programApplied;

    @Column(name = "submitted_at")
    private OffsetDateTime submittedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Version
    private Long version;

    @PrePersist
    public void prePersist() {
        createdAt = OffsetDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = OffsetDateTime.now();
    }

    public enum ApplicationStatus { PENDING, IN_REVIEW, ACCEPTED, REJECTED }
    public enum ApplicationStep {
        STARTED,
        PERSONAL_INFO_COMPLETED,
        EDUCATION_COMPLETED,
        DOCUMENTS_UPLOADED,
        SUBMITTED
    }
    public enum Program { CS, ENG, MED, BUSINESS, OTHER }
}

