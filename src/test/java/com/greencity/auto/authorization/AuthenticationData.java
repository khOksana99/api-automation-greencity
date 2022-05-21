package com.greencity.auto.authorization;

import com.greencity.auto.utils.Util;
import lombok.Data;

@Data
public class AuthenticationData {
    private final String username;
    private final String password;
    private final String nikName = "Test123";

    public AuthenticationData() {
        this.username = Util.SECURE_PROPS.username();
        this.password = Util.SECURE_PROPS.password();
    }

    @Override
    public String toString() {
        return "{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
