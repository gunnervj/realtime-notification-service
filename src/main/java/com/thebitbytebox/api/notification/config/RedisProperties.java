package com.thebitbytebox.api.notification.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "notification-cache")
@ToString
public class RedisProperties {
    private String host;
    private Boolean standalone = false;
    private Long ttl;
    private Long maxIdleTime;
    private Boolean ssl = false;
}
