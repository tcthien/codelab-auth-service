package com.tts.codelab.auth.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.tts.codelab.auth.domain.User;
import com.tts.codelab.auth.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PreAuthorize("#authentication.name == username")
    @RequestMapping(value = "/pass", method = RequestMethod.PUT)
    public void updatePassword(@RequestParam("username") String username, @RequestParam("oldPass") String oldPass,
                               @RequestParam("newPass") String newPass) {
        userService.updatePassword(username, oldPass, newPass);
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@Valid @RequestBody User user) {
        userService.create(user);
    }

    @PreAuthorize("#oauth2.hasScope('server') && authentication.name == user.username")
    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@Valid @RequestBody User user) {
        userService.update(user);
    }
}
