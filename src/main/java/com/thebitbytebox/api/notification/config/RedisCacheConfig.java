package com.thebitbytebox.api.notification.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
@RequiredArgsConstructor
@EnableCaching
public class RedisCacheConfig {
    private final RedisProperties redisProperties;
    public static final String NOTIFICATION_CLIENT_CACHE = "notificationClientCache";

    @Bean("notificationCacheConfig")
    public Config redissonConfig() {
        Assert.hasText(redisProperties.getHost(), "Error configuring redis cache. Missing Host");
        Config config = new Config();
        String prefix = redisProperties.getSsl() ? "rediss://" : "redis://";
        if (redisProperties.getStandalone()) {
            config.useSingleServer().setAddress(prefix + redisProperties.getHost());
        } else {
            config.useReplicatedServers().addNodeAddress(prefix + redisProperties.getHost());
            config.useReplicatedServers().setReadMode(ReadMode.MASTER);
        }
        return config;
    }

    @Bean({"notificationCacheManager"})
    public CacheManager cacheManager(RedissonClient notificationRedissonClient) throws IOException {
        Map<String, CacheConfig> cacheConfigMap = new HashMap<>();
        cacheConfigMap.put(NOTIFICATION_CLIENT_CACHE, new CacheConfig(redisProperties.getTtl(), redisProperties.getMaxIdleTime()));
        return new RedissonSpringCacheManager(notificationRedissonClient, cacheConfigMap);
    }

    @Bean(value = "notificationRedissonClient", destroyMethod = "shutdown")
    public RedissonClient redissonClient(Config notificationCacheConfig) {
        log.info("REDIS CONFIG: " + redisProperties.toString());
        return Redisson.create(notificationCacheConfig);
    }

}
