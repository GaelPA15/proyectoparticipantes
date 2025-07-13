package com.universidad.participantesapi.service;

import com.universidad.participantesapi.dto.ParticipantRequest;
import com.universidad.participantesapi.dto.ParticipantResponse;
import com.universidad.participantesapi.model.Participant.Category;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ParticipantService {

    ParticipantResponse createParticipant(ParticipantRequest request);

    List<ParticipantResponse> getAllParticipants();

    ParticipantResponse getParticipantById(UUID id);

    ParticipantResponse updateParticipant(UUID id, ParticipantRequest request);

    void deleteParticipant(UUID id);

    List<ParticipantResponse> getParticipantsByCategory(Category category);

    Map<Category, Long> getParticipantsReport();
}
