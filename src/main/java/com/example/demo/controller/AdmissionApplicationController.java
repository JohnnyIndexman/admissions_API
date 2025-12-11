//package com.example.demo.controller;
//
//import com.example.demo.model.AdmissionApplication;
//import com.example.demo.repository.AdmissionApplicationRepository;
//import jakarta.validation.Valid;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.example.demo.dto.CreateApplicationRequest;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/applications")
//public class AdmissionApplicationController {
//
//    private final AdmissionApplicationRepository repository;
//
//    public AdmissionApplicationController(AdmissionApplicationRepository repository) {
//        this.repository = repository;
//    }
//
//    /** Get all applications with pagination/sorting */
//    @GetMapping
//    public Page<AdmissionApplication> getAll(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "createdAt") String sortBy,
//            @RequestParam(defaultValue = "desc") String sortDir
//    ) {
//        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
//        return repository.findAll(PageRequest.of(page, size, sort));
//    }
//
//    /** Get single application by ID */
//    @GetMapping("/{id}")
//    public ResponseEntity<AdmissionApplication> getById(@PathVariable UUID id) {
//        return repository.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    /** Create a new application */
//    @PostMapping
//    public ResponseEntity<AdmissionApplication> create(@Valid @RequestBody AdmissionApplication request) {
//        AdmissionApplication saved = repository.save(request);
//        return new ResponseEntity<>(saved, HttpStatus.CREATED);
//    }
//
//    /** Update application (full update) */
////    @PutMapping("/{id}")
////    public ResponseEntity<AdmissionApplication> update(
////            @PathVariable UUID id,
////            @Valid @RequestBody AdmissionApplication updateRequest
////    ) {
////        Optional<AdmissionApplication> existingOpt = repository.findById(id);
////        if (existingOpt.isEmpty()) return ResponseEntity.notFound().build();
////
////        AdmissionApplication existing = existingOpt.get();
////        existing.setApplicantName(updateRequest.getApplicantName());
////        existing.setEmail(updateRequest.getEmail());
////        existing.setPhone(updateRequest.getPhone());
////        existing.setProgramApplied(updateRequest.getProgramApplied());
////        existing.setCurrentStep(updateRequest.getCurrentStep());
////        existing.setStatus(updateRequest.getStatus());
////
////        repository.save(existing);
////        return ResponseEntity.ok(existing);
////    }
//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(
//            @PathVariable UUID id,
//            @Valid @RequestBody CreateApplicationRequest updateRequest
//    ) {
//        return repository.findById(id)
//                .map(existing -> {
//                    existing.setApplicantName(updateRequest.getApplicantName());
//                    existing.setEmail(updateRequest.getEmail());
//                    existing.setPhone(updateRequest.getPhone());
//                    existing.setProgramApplied(updateRequest.getProgramApplied());
//                    repository.save(existing);
//                    return ResponseEntity.ok(existing);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//
//    /** Soft delete */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> softDelete(@PathVariable UUID id) {
//        Optional<AdmissionApplication> existingOpt = repository.findById(id);
//        if (existingOpt.isEmpty()) return ResponseEntity.notFound().build();
//
//        AdmissionApplication existing = existingOpt.get();
//        existing.setDeleted(true);
//        repository.save(existing);
//        return ResponseEntity.noContent().build();
//    }
//
//    /** Update application status */
//    @PatchMapping("/{id}/status")
//    public ResponseEntity<AdmissionApplication> updateStatus(
//            @PathVariable UUID id,
//            @RequestParam AdmissionApplication.ApplicationStatus status
//    ) {
//        return repository.findById(id).map(app -> {
//            app.setStatus(status);
//            repository.save(app);
//            return ResponseEntity.ok(app);
//        }).orElse(ResponseEntity.notFound().build());
//    }
//
//    /** Update current step */
//    @PatchMapping("/{id}/step")
//    public ResponseEntity<AdmissionApplication> updateStep(
//            @PathVariable UUID id,
//            @RequestParam AdmissionApplication.ApplicationStep step
//    ) {
//        return repository.findById(id).map(app -> {
//            app.setCurrentStep(step);
//            repository.save(app);
//            return ResponseEntity.ok(app);
//        }).orElse(ResponseEntity.notFound().build());
//    }
//
//    /**
//     * Search by email or program
//     */
//    @GetMapping("/search")
//    public List<@org.jetbrains.annotations.NotNull Object> search(
//            @RequestParam(required = false) String email,
//            @RequestParam(required = false) AdmissionApplication.Program program
//    ) {
//        if (email != null) {
//            return repository.findByEmailAndDeletedFalse(email)
//                    .map(List::of)
//                    .orElse(List.of());
//        } else if (program != null) {
//            return Collections.singletonList(repository.findAll().stream()
//                    .filter(app -> !app.isDeleted() && app.getProgramApplied() == program)
//                    .toList());
//        }
//        return List.of();
//    }
//}
package com.example.demo.controller;

import com.example.demo.dto.CreateApplicationRequest;
import com.example.demo.model.AdmissionApplication;
import com.example.demo.repository.AdmissionApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
public class AdmissionApplicationController {

    private final AdmissionApplicationRepository repository;

    public AdmissionApplicationController(AdmissionApplicationRepository repository) {
        this.repository = repository;
    }

    /** Get all applications with pagination + sorting */
    @GetMapping
    public Page<AdmissionApplication> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return repository.findAll(PageRequest.of(page, size, sort));
    }

    /** Get one application by ID */
    @GetMapping("/{id}")
    public ResponseEntity<AdmissionApplication> getById(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Create new application */
    @PostMapping
    public ResponseEntity<AdmissionApplication> create(
            @Valid @RequestBody CreateApplicationRequest request
    ) {
        AdmissionApplication app = AdmissionApplication.builder()
                .applicantName(request.getApplicantName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .programApplied(request.getProgramApplied())
                .build();

        AdmissionApplication saved = repository.save(app);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    /** Update basic fields of application */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @Valid @RequestBody CreateApplicationRequest updateRequest
    ) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setApplicantName(updateRequest.getApplicantName());
                    existing.setEmail(updateRequest.getEmail());
                    existing.setPhone(updateRequest.getPhone());
                    existing.setProgramApplied(updateRequest.getProgramApplied());
                    repository.save(existing);
                    return ResponseEntity.ok(existing);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /** Soft delete application */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDelete(@PathVariable UUID id) {
        Optional<AdmissionApplication> existingOpt = repository.findById(id);
        if (existingOpt.isEmpty()) return ResponseEntity.notFound().build();

        AdmissionApplication existing = existingOpt.get();
        existing.setDeleted(true);
        repository.save(existing);

        return ResponseEntity.noContent().build();
    }

    /** Update application status */
    @PatchMapping("/{id}/status")
    public ResponseEntity<AdmissionApplication> updateStatus(
            @PathVariable UUID id,
            @RequestParam AdmissionApplication.ApplicationStatus status
    ) {
        return repository.findById(id)
                .map(app -> {
                    app.setStatus(status);
                    repository.save(app);
                    return ResponseEntity.ok(app);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /** Update the current step of an application */
    @PatchMapping("/{id}/step")
    public ResponseEntity<AdmissionApplication> updateStep(
            @PathVariable UUID id,
            @RequestParam AdmissionApplication.ApplicationStep step
    ) {
        return repository.findById(id)
                .map(app -> {
                    app.setCurrentStep(step);
                    repository.save(app);
                    return ResponseEntity.ok(app);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /** Search by email or program */
    @GetMapping("/search")
    public List<? extends Object> search(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) AdmissionApplication.Program program
    ) {
        if (email != null) {
            return repository.findByEmailAndDeletedFalse(email)
                    .map(List::of)
                    .orElse(List.of());
        }

        if (program != null) {
            return repository.findAll()
                    .stream()
                    .filter(app -> !app.isDeleted() && app.getProgramApplied() == program)
                    .toList();
        }

        return List.of();
    }
}
