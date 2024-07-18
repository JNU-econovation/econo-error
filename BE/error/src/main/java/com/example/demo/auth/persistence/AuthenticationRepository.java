package com.example.demo.auth.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class AuthenticationRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void save(String token, Long memberId, Long expiration) {
        redisTemplate.opsForValue().set(token, memberId, expiration, TimeUnit.MILLISECONDS);
    }

    public Boolean isExistToken(String key) {
        return redisTemplate.hasKey(key);
    }
}

