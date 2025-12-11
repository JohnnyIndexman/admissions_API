package com.example.demo.dto;

import com.example.demo.model.AdmissionApplication;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UpdateApplicationRequest {
    @Size(max = 255)
    private String applicantName;

    @Email
    private String email;

    @Size(max = 50)
    private String phone;

    private AdmissionApplication.Program programApplied;

    @NotNull
    private Long version;
}

