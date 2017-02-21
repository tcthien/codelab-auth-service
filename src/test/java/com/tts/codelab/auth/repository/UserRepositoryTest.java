package com.tts.codelab.auth.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tts.codelab.auth.CodelabAuthServiceApplication;
import com.tts.codelab.auth.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CodelabAuthServiceApplication.class)
public class UserRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName() {
        User user = new User();
        user.setUsername("name");
        user.setPassword("password");
        userRepository.save(user);

        User found = userRepository.findOne(user.getUsername());
        assertEquals(user.getUsername(), found.getUsername());
        assertEquals(user.getPassword(), found.getPassword());
    }
}
