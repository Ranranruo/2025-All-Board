package sms.allBoard.Common.Util.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class StringRedisUtil {
    @Autowired
    @Qualifier("tokenRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value, 7, TimeUnit.DAYS);
    }
    public String get(String key) { return redisTemplate.opsForValue().get(key);}
    public void delete(String key) { redisTemplate.delete(key); }
}