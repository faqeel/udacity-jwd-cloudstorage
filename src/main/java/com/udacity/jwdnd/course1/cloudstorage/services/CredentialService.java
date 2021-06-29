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
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentialsByUserId(Integer userId) {
        List<Credential> credentials = credentialMapper.getByUserId(userId);
        return credentials;
    }

    public int store(Credential credential, User user) {
        String encodedKey = generateEncodedKey();
        String encryptedPassword = encryptPassword(credential.getPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setUserId(user.getUserId());
        return credentialMapper.insert(credential);
    }

    public int update(Credential credential) {
        String encodedKey = generateEncodedKey();
        String encryptedPassword = encryptPassword(credential.getPassword(), encodedKey);
        credential.setKey(encodedKey);
        credential.setPassword(encryptedPassword);
        return credentialMapper.update(credential);
    }

    public int deleteById(String credentialId) {
        return credentialMapper.deleteById(credentialId);
    }

    private String generateEncodedKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    private String encryptPassword(String password, String key) {
        return encryptionService.encryptValue(password, key);
    }
}
