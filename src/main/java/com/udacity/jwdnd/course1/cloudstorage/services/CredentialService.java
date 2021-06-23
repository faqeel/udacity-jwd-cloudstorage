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
        List<Credential> credentials = credentialMapper.getCredentialByUser(user.getUserId());
        System.out.println(credentials);
        return credentials;
    }

    public int store(Credential credential, User user) {
        credential.setKey("");
        credential.setUserId(user.getUserId());
        return credentialMapper.insert(credential);
    }
}
