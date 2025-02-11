package ru.t1.starter;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "logger")
public class LoggerProperties {
    private Boolean enabled;
    private Level level;

    public LoggerProperties(Boolean enabled, Level level) {
        this.enabled = enabled;
        this.level = level;
    }
}
