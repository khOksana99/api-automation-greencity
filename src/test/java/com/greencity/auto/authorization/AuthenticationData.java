package com.greencity.auto.authorization;

import com.greencity.auto.utils.Util;
import lombok.Data;

@Data
public class AuthenticationData {
    private final String email;
    private final String password;
    private final String nikName = "Test123";

    public AuthenticationData() {
        this.email = Util.SECURE_PROPS.username();
        this.password = Util.SECURE_PROPS.password();
    }

    @Override
    public String toString() {
        return "{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
