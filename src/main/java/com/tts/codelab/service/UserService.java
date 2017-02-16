package com.tts.codelab.service;

import java.util.List;

import com.tts.codelab.domain.User;

public interface UserService {

    void create(User user);
    
    List<User> findAll();

}
