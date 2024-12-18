package com.decathlon.domain_name.technical.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneOffset;
import java.util.TimeZone;

@Configuration
public class ApplicationConfig {

    /**
     * Set default time zone for dates to UTC.
     */
    @PostConstruct
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
    }
}
