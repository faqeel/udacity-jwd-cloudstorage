package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
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

        if (fileService.isFileExists(file.getOriginalFilename())) {
            message = "The file name already exists.";
            model.addAttribute("success", false);
            model.addAttribute("message", message);
            return "home";
        }

        int rowAdded = fileService.store(file, userService.getUser(principal.getName()));
        if (rowAdded < 0) {
            message = "There was an error while uploading the file. Please try again.";
            model.addAttribute("success", false);
            model.addAttribute("message", message);
            return "home";
        }

        model.addAttribute("success", true);
        return "home";
    }
}
