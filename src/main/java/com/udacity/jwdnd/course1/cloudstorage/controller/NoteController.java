package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public String store(Authentication authentication, @ModelAttribute Note note, Model model) {
        String message;
        User user = userService.getUserByUsername(authentication.getName());
        model.addAttribute("directTo", "home");
        int rowAdded = note.getNoteId() == null ? noteService.store(note, user) : noteService.update(note);
        if (rowAdded <= 0) {
            message = "There was an error while processing the note. Please try again.";
            model.addAttribute("success", false);
            model.addAttribute("message", message);
            return "result";
        }
        model.addAttribute("success", true);
        return "result";
    }

    @GetMapping(value = "/{noteId}/delete")
    public String deleteNoteById(@PathVariable Integer noteId, Model model) {
        String message;
        model.addAttribute("directTo", "home");
        int rowDeleted = noteService.deleteById(noteId);
        if (rowDeleted <= 0) {
            message = "There was an error while deleting the note. Please try again.";
            model.addAttribute("success", false);
            model.addAttribute("message", message);
            return "result";
        }
        model.addAttribute("success", true);
        return "result";
    }
}
