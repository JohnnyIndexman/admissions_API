package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReviewerComment{
    @NotBlank
    private String reviewer;

    @NotBlank
    private String comment;
}

