package utils;

import config.TestConfig;
import org.aeonbits.owner.ConfigFactory;

public class OwnerConfig {

    private static final TestConfig CONFIG =
            ConfigFactory.create(TestConfig.class, System.getProperties());

    public static TestConfig get() {
        return CONFIG;
    }
}