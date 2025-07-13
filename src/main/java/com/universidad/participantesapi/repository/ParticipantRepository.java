package com.universidad.participantesapi.repository;

import com.universidad.participantesapi.model.Participant;
import com.universidad.participantesapi.model.Participant.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    Optional<Participant> findByEmail(String email);
    List<Participant> findByCategory(Category category);
}
