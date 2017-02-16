package com.tts.codelab.domain;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class UserAuthority implements GrantedAuthority {

    private String authority;
    
    public UserAuthority() {
    }

    public UserAuthority(String authority) {
        this.authority = authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
    @Override
    public String getAuthority() {
        return authority;
    }

}
