package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {
    private CredentialMapper credentialMapper;
    EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentialsByUser(User user) {
        List<Credential> credentials = credentialMapper.getCredentialByUser(user.getUserId());
        credentials.forEach(credential -> {
            String encryptedPassword = credential.getPassword();
            String encodedKey = credential.getKey();
            String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
            credential.setPassword(decryptedPassword);
        });
        return credentials;
    }

    public int store(Credential credential, User user) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setUserId(user.getUserId());
        return credentialMapper.insert(credential);
    }

    public int update(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        return credentialMapper.update(credential);
    }

    public int deleteById(String credentialId) {
        return credentialMapper.deleteByName(credentialId);
    }
}
