package ru.t1.starter;

import org.slf4j.event.Level;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.t1.starter.aop.LogAspect;
import ru.t1.starter.aop.LogAspectConfig;

@Configuration
@EnableConfigurationProperties(LoggerProperties.class)
public class LoggerAutoConfiguration {
    private final LoggerProperties loggerProperties;

    public LoggerAutoConfiguration(LoggerProperties loggerProperties) {
        this.loggerProperties = loggerProperties;
    }

    @Bean
    public LogAspectConfig logAspectConfig() {
        Boolean enabled = loggerProperties.getEnabled();
        Level level = loggerProperties.getLevel();
        if (level == null)
            level = Level.INFO;
        return new LogAspectConfig(enabled, level);
    }

    @Bean
    @ConditionalOnProperty(name = "logger.enabled", havingValue = "true")
    public LogAspect logAspect(LogAspectConfig logAspectConfig) {
        return new LogAspect(logAspectConfig);
    }
}
