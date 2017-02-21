package com.tts.codelab.auth.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.security.auth.UserPrincipal;
import com.tts.codelab.auth.CodelabAuthServiceApplication;
import com.tts.codelab.auth.domain.User;
import com.tts.codelab.auth.service.UserService;

@SuppressWarnings("restriction")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CodelabAuthServiceApplication.class)
@WebAppConfiguration
public class UserControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();
    
    @InjectMocks
    private UserController userController;
    
    @Mock
    private UserService userService;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    
    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUsername("tcthien");
        user.setPassword("tcthien");
        user.setFullName("tcthien");
        user.setEmail("thientran1986@gmail.com");
        
        String json = mapper.writeValueAsString(user);
        
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json))
            .andExpect(status().isOk());
    }
    
    @Test
    public void testCurrentUser() throws Exception {
        mockMvc.perform(get("/users/current").principal(new UserPrincipal("test")))
            .andExpect(jsonPath("$.name").value("test"))
            .andExpect(status().isOk());
    }
    
    @Test
    public void testUserNameIsNull() throws Exception {
        User user = new User();
        user.setPassword("tcthien");
        user.setFullName("tcthien");
        user.setEmail("thientran1986@gmail.com");
        
        String json = mapper.writeValueAsString(user);
        
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(json))
            .andExpect(status().isBadRequest());
    }
}
