package config;

import org.aeonbits.owner.Config;
import java.time.Duration;

@Config.Sources({
    "classpath:${env}.properties",
    "classpath:test.properties"
})
public interface TestConfig extends Config {

    @Key("base.url")
    String baseUrl();

    @Key("timeout.default")
    @DefaultValue("10")
    int defaultTimeout();

    @Key("timeout.short")
    @DefaultValue("5")
    int shortTimeout();

    @Key("timeout.long")
    @DefaultValue("15")
    int longTimeout();
}