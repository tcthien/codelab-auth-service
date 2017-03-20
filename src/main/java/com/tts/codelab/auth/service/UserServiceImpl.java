package com.tts.codelab.auth.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.tts.codelab.auth.domain.User;
import com.tts.codelab.auth.repository.UserRepository;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    @Autowired
    private UserRepository repository;

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword) {
        String pass = encoder.encode(oldPassword);
        User existing = repository.findOneByUsernameAndPassword(username, pass);
        Assert.notNull(existing, "username or password is invalid: " + username + " - " + oldPassword);
        existing.setPassword(encoder.encode(newPassword));
        repository.save(existing);
    }

    @Override
    public void update(User user) {
        User existing = repository.findOne(user.getUsername());
        Assert.notNull(existing, "user not found: " + user.getUsername());

        existing.setFullName(user.getFullName());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existing.setPassword(encoder.encode(user.getPassword()));
        }
        existing.setEmail(user.getEmail());
        repository.save(existing);
    }

    @Override
    public void create(User user) {
        User existing = repository.findOne(user.getUsername());
        Assert.isNull(existing, "user already exists: " + user.getUsername());

        String hash = encoder.encode(user.getPassword());
        user.setPassword(hash);

        repository.save(user);

        log.info("new user has been created: {}", user.getUsername());        
    }
    
    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

}
