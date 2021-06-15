package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/files")
public class FileController {
    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Principal principal, Model model) {
        String message;
        User user = userService.getUser(principal.getName());

        model.addAttribute("directTo", "home");

        if (fileService.isFileExists(file.getOriginalFilename())) {
            message = "The file name already exists.";
            model.addAttribute("success", false);
            model.addAttribute("message", message);
            return "result";
        }

        int rowAdded = fileService.store(file, user);
        if (rowAdded < 0) {
            message = "There was an error while uploading the file. Please try again.";
            model.addAttribute("success", false);
            model.addAttribute("message", message);
            return "result";
        }

        model.addAttribute("success", true);
        return "result";
    }
}
