package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(
            value = "/{fileName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody byte[] getFile(@PathVariable String fileName) {
        return fileService.getFileByName(fileName).getFileData();
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

    @GetMapping(value = "/{fileName}/delete")
    public String deleteFile(@PathVariable String fileName, Model model) {
        String message;

        model.addAttribute("directTo", "home");

        int rowAdded = fileService.deleteByName(fileName);
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
