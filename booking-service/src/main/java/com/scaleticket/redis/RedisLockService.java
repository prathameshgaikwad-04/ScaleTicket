package com.scaleticket.redis;

import java.time.Duration;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisLockService {

    private final StringRedisTemplate redisTemplate;

    // ✅ Explicit constructor (Spring will inject this)
    public RedisLockService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean lockSeat(String key, String value) {
        return Boolean.TRUE.equals(
            redisTemplate.opsForValue()
                .setIfAbsent(key, value, Duration.ofSeconds(10))
        );
    }

    public void releaseLock(String key, String value) {
        String current = redisTemplate.opsForValue().get(key);
        if (value.equals(current)) {
            redisTemplate.delete(key);
        }
    }
}


