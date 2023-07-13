package com.example.employeeManagement.CacheConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;



//@Service
@Component
public class RedisClient {


    @Autowired
    private final RedisTemplate<String, String> redisTemplate;


    public RedisClient(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void setKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
//        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    public String getKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteKey(String key){
        redisTemplate.delete(key);
    }

}
