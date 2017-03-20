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

    @RequestMapping(value = "/pass", method = RequestMethod.PUT)
    public void updatePassword(@RequestParam("username") String userName, @RequestParam("oldPass") String oldPassword,
                               @RequestParam("newPass") String newPassword) {
        userService.updatePassword(userName, oldPassword, newPassword);
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@Valid @RequestBody User user) {
        userService.create(user);
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@Valid @RequestBody User user) {
        userService.update(user);
    }
}
