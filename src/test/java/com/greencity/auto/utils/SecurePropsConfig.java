package com.greencity.auto.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class SecurePropsConfig {
    public String username() {
        return System.getProperty("username") != null
                ? System.getProperty("username")
                : getSecuredPropertyValueByKey("username");
    }

    public String password() {
        return System.getProperty("password") != null
                ? System.getProperty("password")
                : getSecuredPropertyValueByKey("password");
    }

    private static String getSecuredPropertyValueByKey(String key) {
        String value = null;
        String propertiesPath = Paths.get(System.getProperty("user.dir"), "src/test/resources/secure.properties").normalize().toString();
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(propertiesPath));
            value = properties.getProperty(key);
        } catch (IOException e) {

        }
        return value;
    }
}
