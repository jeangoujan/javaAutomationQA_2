package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream is = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("test.properties")) {

            if (is == null) {
                throw new RuntimeException("test.properties not found");
            }

            properties.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String getSecret(String key) {
    // преобразуем ключ в ENV-формат
    String envKey = key.toUpperCase().replace('.', '_');

    // 1. пробуем ENV
    String envValue = System.getenv(envKey);
    if (envValue != null && !envValue.isBlank()) {
        return envValue;
    }

    // 2. fallback в properties
    String propValue = properties.getProperty(key);
    if (propValue != null && !propValue.isBlank()) {
        return propValue;
    }

    // 3. если нигде нет — это ошибка
    throw new RuntimeException("Secret not found: " + key);
}
}