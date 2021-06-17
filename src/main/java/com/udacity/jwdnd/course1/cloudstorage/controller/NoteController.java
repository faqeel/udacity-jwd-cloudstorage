package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        User user = userService.getUser(authentication.getName());

        model.addAttribute("directTo", "home");

        int rowAdded = noteService.store(note, user);
        if (rowAdded < 0) {
            message = "There was an error while creating the note. Please try again.";
            model.addAttribute("success", false);
            model.addAttribute("message", message);
            return "result";
        }

        model.addAttribute("success", true);
        return "result";
    }
}
