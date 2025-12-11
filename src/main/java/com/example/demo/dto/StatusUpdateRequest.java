package com.example.demo.dto;

import com.example.demo.model.AdmissionApplication;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class StatusUpdateRequest {
    @NotNull
    private AdmissionApplication.ApplicationStatus status;
}

