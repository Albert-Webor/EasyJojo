package com.easyjojo.common.config;

import com.easyjojo.common.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {RedisConfig.class, RedisUtil.class})
public class RedisConnectionTest {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testRedisConnection() {
        assertNotNull(connectionFactory, "RedisConnectionFactory should not be null");
        try (RedisConnection connection = connectionFactory.getConnection()) {
            String pingResult = connection.ping();
            assertEquals("PONG", pingResult, "Redis ping should return PONG");
        }
    }

    @Test
    public void testRedisTemplateOperations() {
        String key = "test:template:key";
        String value = "hello-from-my-redis";

        redisTemplate.opsForValue().set(key, value);
        Object retrieved = redisTemplate.opsForValue().get(key);
        assertEquals(value, retrieved);

        redisTemplate.delete(key);
        assertNull(redisTemplate.opsForValue().get(key));
    }

    @Test
    public void testRedisUtilOperations() {
        String key = "test:util:key";
        String value = "easyjojo-redis-test";

        boolean setResult = redisUtil.set(key, value);
        assertTrue(setResult);
        assertTrue(redisUtil.hasKey(key));

        Object retrieved = redisUtil.get(key);
        assertEquals(value, retrieved);

        redisUtil.del(key);
        assertFalse(redisUtil.hasKey(key));
    }
}
