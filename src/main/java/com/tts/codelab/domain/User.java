package com.tts.codelab.domain;

import java.util.Arrays;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
@Document(collection = "users")
public class User implements UserDetails {

    @Id
    @NotNull
    private String username;

    @NotNull
    @Length(min = 6, max = 40)
    private String password;
    
    @NotNull
    private String fullName;

    @NotNull
    private String email;
    
    private String role = "USER";

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Arrays.asList(new UserAuthority(role));
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
