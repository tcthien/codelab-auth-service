package com.tts.codelab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tts.codelab.domain.User;
import com.tts.codelab.repository.UserRepository;

@Service
public class MongoUserDetailServiceImpl implements MongoUserDetailService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findOne(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

}
