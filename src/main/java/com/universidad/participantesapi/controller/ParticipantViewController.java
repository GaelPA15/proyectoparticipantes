package com.universidad.participantesapi.controller;

import com.universidad.participantesapi.dto.ParticipantResponse;
import com.universidad.participantesapi.dto.ParticipantRequest;
import com.universidad.participantesapi.model.Participant.Category;
import com.universidad.participantesapi.service.ParticipantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/participants")
public class ParticipantViewController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public String listParticipants(Model model) {
        List<ParticipantResponse> participants = participantService.getAllParticipants();
        model.addAttribute("participants", participants);
        return "participants";  // Nombre de la plantilla Thymeleaf
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("participantRequest", new ParticipantRequest());
        model.addAttribute("categories", Category.values());
        return "participant_form";
    }

    @PostMapping("/new")
    public String createParticipant(@Valid @ModelAttribute("participantRequest") ParticipantRequest participantRequest,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", Category.values());
            return "participant_form";
        }
        try {
            participantService.createParticipant(participantRequest);
        } catch (RuntimeException e) {
            bindingResult.rejectValue("email", "error.participantRequest", e.getMessage());
            model.addAttribute("categories", Category.values());
            return "participant_form";
        }
        return "redirect:/participants";
    }
}
