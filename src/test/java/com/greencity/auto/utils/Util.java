package com.greencity.auto.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class Util {
    public static final SecurePropsConfig SECURE_PROPS = new SecurePropsConfig();
    private static final DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd").toFormatter();

    public static String getCurrentDateTime() {
        return LocalDateTime.now().toString();
    }

    public static String getCurrentDate() {
        return ZonedDateTime.now(ZoneOffset.UTC).format(dateTimeFormatter);
    }

    public static String getFutureDate() {
        return ZonedDateTime.now(ZoneOffset.UTC).plusDays(1).format(dateTimeFormatter);
    }

    public static String getPastDate() {
        return ZonedDateTime.now(ZoneOffset.UTC).minusDays(1).format(dateTimeFormatter);
    }
}
