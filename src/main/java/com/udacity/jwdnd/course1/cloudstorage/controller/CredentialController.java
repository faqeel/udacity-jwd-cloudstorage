package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping
    String store(Authentication authentication, @ModelAttribute Credential credential, Model model) {
        String message;
        User user = userService.getUser(authentication.getName());

        model.addAttribute("directTo", "home");

        int rowAdded = credentialService.store(credential, user);
        if (rowAdded < 0) {
            message = "There was an error while storing the credential. Please try again.";
            model.addAttribute("success", false);
            model.addAttribute("message", message);
            return "result";
        }

        model.addAttribute("success", true);
        return "result";
    }

    @GetMapping(value = "/{credentialId}/delete")
    public String deleteFile(@PathVariable String credentialId, Model model) {
        String message;

        model.addAttribute("directTo", "home");

        int rowAdded = credentialService.deleteById(credentialId);
        if (rowAdded < 0) {
            message = "There was an error while deleting the credential. Please try again.";
            model.addAttribute("success", false);
            model.addAttribute("message", message);
            return "result";
        }

        model.addAttribute("success", true);
        return "result";
    }
}
