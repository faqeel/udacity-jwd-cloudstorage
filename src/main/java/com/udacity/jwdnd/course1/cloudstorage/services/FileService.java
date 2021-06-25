package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File getFileByName(String fileName) {
        return fileMapper.getFileByFileName(fileName);
    }

    public List<File> getFilesByUserId(Integer userId) {
        return fileMapper.getFilesByUserId(userId);
    }

    public int store(MultipartFile multipartFile, User user) {
        if (multipartFile.isEmpty()) {
            return 0;
        }
        File file;
        try {
            file = new File(multipartFile, user.getUserId());
        } catch (IOException e) {
            return 0;
        }
        return fileMapper.insert(file);
    }

    public int update(File file) {
        return fileMapper.update(file);
    }

    public int deleteByName(String fileName) {
        return fileMapper.deleteByName(fileName);
    }

    public boolean isFileExists(String fileName) {
        return fileMapper.getFileByFileName(fileName) != null;
    }
}
