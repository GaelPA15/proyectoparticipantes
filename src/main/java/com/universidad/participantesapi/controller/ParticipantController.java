package com.universidad.participantesapi.controller;

import com.universidad.participantesapi.dto.ParticipantRequest;
import com.universidad.participantesapi.dto.ParticipantResponse;
import com.universidad.participantesapi.model.Participant.Category;
import com.universidad.participantesapi.service.ParticipantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService service;

    public ParticipantController(ParticipantService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<ParticipantResponse> createParticipant(@Valid @RequestBody ParticipantRequest request) {
        ParticipantResponse response = service.createParticipant(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ParticipantResponse>> getAllParticipants() {
        return ResponseEntity.ok(service.getAllParticipants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipantResponse> getParticipantById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getParticipantById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipantResponse> updateParticipant(@PathVariable UUID id,
                                                                 @Valid @RequestBody ParticipantRequest request) {
        return ResponseEntity.ok(service.updateParticipant(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable UUID id) {
        service.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ParticipantResponse>> getByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(service.getParticipantsByCategory(category));
    }

    @GetMapping("/report")
    public ResponseEntity<Map<Category, Long>> getParticipantsReport() {
        return ResponseEntity.ok(service.getParticipantsReport());
    }
}
