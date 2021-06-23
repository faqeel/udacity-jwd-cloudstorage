package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getCredentialsByUser(User user) {
        return credentialMapper.getCredentialByUser(user.getUserId());
    }

    public int store(Credential credential, User user) {
        credential.setKey("");
        credential.setUserId(user.getUserId());
        return credentialMapper.insert(credential);
    }

    public int update(Credential credential) {
        credential.setKey("");
        return credentialMapper.update(credential);
    }

    public int deleteById(String credentialId) {
        return credentialMapper.deleteByName(credentialId);
    }
}
