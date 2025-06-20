package sms.allBoard.Common.Util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisUtil {
    private final RedisTemplate<String, String> redisTemplate;
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value, 7, TimeUnit.DAYS);
    }
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    public void delete(String key) { redisTemplate.delete(key); }
}