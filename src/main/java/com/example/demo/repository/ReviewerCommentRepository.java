package com.example.demo.repository;

import com.example.demo.model.ReviewerCommentRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewerCommentRepository extends JpaRepository<ReviewerCommentRequest, UUID> {
    List<ReviewerCommentRequest> findByApplicationIdOrderByCreatedAtDesc(UUID applicationId);
}
