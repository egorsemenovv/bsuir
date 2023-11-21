package org.example.database;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {
    public static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    /**
     * gets properties from HashTable
     * @param key property name
     * @return value from HashTable
     */
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    /**
     * load properties for database connection from application.properties
     */
    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
