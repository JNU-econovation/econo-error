package com.example.demo.auth.persistence;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash("MemberAuthentication")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationEntity {
    @Id
    private String token;
    private Long memberId;
    @TimeToLive
    private Long expiration;
}