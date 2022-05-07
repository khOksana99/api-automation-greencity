package com.greencity.auto.utils;

import java.time.LocalDateTime;

public class Util {
    public static final SecurePropsConfig SECURE_PROPS = new SecurePropsConfig();

    public static String getCurrentDateTime() {
        return LocalDateTime.now().toString();
    }
}
