package com.tts.codelab.auth.service;

import java.util.List;

import com.tts.codelab.auth.domain.User;

public interface UserService {

    void create(User user);
    
    List<User> findAll();

}
