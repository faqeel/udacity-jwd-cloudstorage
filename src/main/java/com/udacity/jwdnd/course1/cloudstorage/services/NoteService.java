package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public int store(Note note, User user) {
        return noteMapper.insert(new Note(note, user.getUserId()));
    }

    public List<Note> getNotesByUser(User user) {
        return noteMapper.getNotesByUser(user.getUserId());
    }

    public int deleteById(Integer noteId) {
        return this.noteMapper.deleteById(noteId);
    }
}
