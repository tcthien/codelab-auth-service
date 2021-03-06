package com.tts.codelab.auth.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tts.codelab.auth.domain.User;
import com.tts.codelab.auth.repository.UserRepository;

public class MongoUserDetailServiceImplTest {

    @InjectMocks
    private MongoUserDetailServiceImpl service;

    @Mock
    private UserRepository repository;

    @Before
    public void setup() {
        initMocks(this);
    }

//    @Test
    public void shouldLoadByUsernameWhenUserExists() {

        final User user = new User();

        when(repository.findOne(any())).thenReturn(user);
        UserDetails loaded = service.loadUserByUsername("name");

        assertEquals(user, loaded);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldFailToLoadByUsernameWhenUserNotExists() {
        service.loadUserByUsername("name");
    }
}
