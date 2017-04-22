package com.tts.codelab.auth;

import com.tts.codelab.auth.domain.UserAuthority;
import org.springframework.security.core.GrantedAuthority;

import java.util.*;

/**
 * Created by tcthien on 4/22/2017.
 */
public class Util {
    public static Set<String> asSet(String... vals) {
        Set<String> rs = new HashSet<>();
        for (String val : vals) {
            rs.add(val);
        }
        return rs;
    }

    public static List<GrantedAuthority> toGrantedAuthority(Set<String> roles) {
        List<GrantedAuthority> lst = new ArrayList<>();
        roles.forEach(role -> {
            lst.add(new UserAuthority(role));
        });
        return lst;
    }
}
