package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final Properties properties = new Properties();
    private static final Properties secrets = new Properties();

    static {
        load("test.properties", properties, true);
        load("secret.properties", secrets, false); // optional
    }

    private static void load(String file, Properties target, boolean required) {
        try (InputStream is = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream(file)) {

            if (is == null) {
                if (required) {
                    throw new RuntimeException(file + " not found");
                }
                return; // secret.properties может не существовать
            }

            target.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load " + file, e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String getSecret(String key) {
        String envKey = key.toUpperCase().replace('.', '_');

        // 1. ENV
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }

        // 2. secret.properties
        String secretValue = secrets.getProperty(key);
        if (secretValue != null && !secretValue.isBlank()) {
            return secretValue;
        }

        throw new RuntimeException("Secret not found: " + key);
    }
}