package ru.t1.starter.aop;

import lombok.Getter;
import org.slf4j.event.Level;

@Getter
public class LogAspectConfig {
    private final Boolean enabled;
    private final Level level;

    public LogAspectConfig(Boolean enabled, Level level) {
        this.enabled = enabled;
        this.level = level;
    }
}
