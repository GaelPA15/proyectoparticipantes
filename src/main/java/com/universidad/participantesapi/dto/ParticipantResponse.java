package com.universidad.participantesapi.dto;

import com.universidad.participantesapi.model.Participant.Category;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParticipantResponse {
    private UUID id;
    private String fullName;
    private String email;
    private String university;
    private Category category;
    private LocalDateTime registrationDate;

    public ParticipantResponse(UUID id, String fullName, String email, String university, Category category, LocalDateTime registrationDate) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.university = university;
        this.category = category;
        this.registrationDate = registrationDate;
    }

    // Getters y setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
