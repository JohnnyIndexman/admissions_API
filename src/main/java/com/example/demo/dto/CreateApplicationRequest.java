////package com.example.demo.dto;
////
////import com.example.demo.model.AdmissionApplication;
////import jakarta.validation.constraints.*;
////import lombok.*;
////
////@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
////public class CreateApplicationRequest {
////    @NotBlank
////    @Size(max = 255)
////    private String applicantName;
////
////    @NotBlank
////    @Email
////    private String email;
////
////    @Size(max = 50)
////    private String phone;
////
////    @NotNull
////    private AdmissionApplication.Program programApplied;
////}
////
//package com.example.demo.dto;
//
//import com.example.demo.model.AdmissionApplication;
//import jakarta.validation.constraints.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Setter
//@Getter
//public class CreateApplicationRequest {
//
//    // Manual setters if needed
//    @NotBlank
//    @Size(max = 255)
//    private String applicantName;
//
//    @NotBlank
//    @Email
//    private String email;
//
//    @Size(max = 50)
//    private String phone;
//
//    @NotNull
//    private AdmissionApplication.Program programApplied;
//
//    // No-args constructor
//    public CreateApplicationRequest() {}
//
//    // All-args constructor
//    public CreateApplicationRequest(String applicantName, String email, String phone, AdmissionApplication.Program programApplied) {
//        this.applicantName = applicantName;
//        this.email = email;
//        this.phone = phone;
//        this.programApplied = programApplied;
//    }
//
//}
package com.example.demo.dto;

import com.example.demo.model.AdmissionApplication;
import jakarta.validation.constraints.*;
import lombok.*;

/**
 * DTO for creating a new AdmissionApplication.
 * Uses Lombok annotations to generate boilerplate code.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateApplicationRequest {

    @NotBlank
    @Size(max = 255)
    private String applicantName;

    @NotBlank
    @Email
    private String email;

    @Size(max = 50)
    private String phone;

    @NotNull
    private AdmissionApplication.Program programApplied;

}
