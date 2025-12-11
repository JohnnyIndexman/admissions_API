package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "reviewer_comments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewerCommentRequest {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private AdmissionApplication application;

    @Column(nullable = false)
    private String reviewer;

    @Column(nullable = false, columnDefinition = "text")
    private String comment;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @PrePersist
    public void prePersist() {
        createdAt = OffsetDateTime.now();
    }
}

