package com.tts.codelab.auth.service;

import java.util.List;

import com.tts.codelab.auth.domain.User;

public interface UserService {

    void create(User user);

    void update(User user);

    List<User> findAll();

    void updatePassword(String userName, String oldPassword, String newPassword);
}
