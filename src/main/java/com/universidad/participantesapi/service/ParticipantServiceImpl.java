package com.universidad.participantesapi.service;

import com.universidad.participantesapi.dto.ParticipantRequest;
import com.universidad.participantesapi.dto.ParticipantResponse;
import com.universidad.participantesapi.exception.EmailAlreadyRegisteredException;
import com.universidad.participantesapi.exception.ParticipantNotFoundException;
import com.universidad.participantesapi.model.Participant;
import com.universidad.participantesapi.model.Participant.Category;
import com.universidad.participantesapi.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository repository;

    public ParticipantServiceImpl(ParticipantRepository repository) {
        this.repository = repository;
    }

    @Override
    public ParticipantResponse createParticipant(ParticipantRequest request) {
        if (repository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException("El email ya está registrado: " + request.getEmail());
        }
        Participant participant = new Participant(
                request.getFullName(),
                request.getEmail(),
                request.getUniversity(),
                request.getCategory()
        );

        Participant saved = repository.save(participant);
        return mapToResponse(saved);
    }

    @Override
    public List<ParticipantResponse> getAllParticipants() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipantResponse getParticipantById(UUID id) {
        Participant p = repository.findById(id)
                .orElseThrow(() -> new ParticipantNotFoundException("Participante no encontrado con ID: " + id));
        return mapToResponse(p);
    }

    @Override
    public ParticipantResponse updateParticipant(UUID id, ParticipantRequest request) {
        Participant participant = repository.findById(id)
                .orElseThrow(() -> new ParticipantNotFoundException("Participante no encontrado con ID: " + id));

        if (!participant.getEmail().equals(request.getEmail()) &&
                repository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyRegisteredException("El email ya está registrado: " + request.getEmail());
        }

        participant.setFullName(request.getFullName());
        participant.setEmail(request.getEmail());
        participant.setUniversity(request.getUniversity());
        participant.setCategory(request.getCategory());

        Participant updated = repository.save(participant);
        return mapToResponse(updated);
    }

    @Override
    public void deleteParticipant(UUID id) {
        if (!repository.existsById(id)) {
            throw new ParticipantNotFoundException("Participante no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<ParticipantResponse> getParticipantsByCategory(Category category) {
        return repository.findByCategory(category)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Category, Long> getParticipantsReport() {
        Map<Category, Long> report = new HashMap<>();
        for (Category cat : Category.values()) {
            long count = repository.findByCategory(cat).size();
            report.put(cat, count);
        }
        return report;
    }

    private ParticipantResponse mapToResponse(Participant participant) {
        return new ParticipantResponse(
                participant.getId(),
                participant.getFullName(),
                participant.getEmail(),
                participant.getUniversity(),
                participant.getCategory(),
                participant.getRegistrationDate()
        );
    }
}
