package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String displaySignupForm() {
        return "signup";
    }

    @PostMapping
    public String submitSignupForm(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        String message;
        if (userService.isUsernameExists(user.getUsername())) {
            message = "The username already exists.";
            model.addAttribute("success", false);
            model.addAttribute("message", message);
            return "signup";
        }
        int rowsAdded = userService.createUser(user);
        if (rowsAdded <= 0) {
            message = "There was an error signing you up. Please try again.";
            model.addAttribute("success", false);
            model.addAttribute("message", message);
            return "signup";
        }
        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("successMessage", "You have successfully signed up!");
        model.addAttribute("success", true);
        return "redirect:/login";
    }
}
