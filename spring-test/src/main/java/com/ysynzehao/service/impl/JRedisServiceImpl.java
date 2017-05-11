package com.ysynzehao.service.impl;

import com.ysynzehao.service.JRedisService;
import com.ysynzehao.utils.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/5.
 */
@Service
public class JRedisServiceImpl implements JRedisService{

    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    @Override
    public boolean set(final String key, final Object value, final long expire) {
    	final StringBuffer v = new StringBuffer();
        if (value instanceof String) {
            v.append((String) value);
        } else {
            v.append(JSONUtil.toJson(value));
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                //connection.set(serializer.serialize(key), serializer.serialize(value));
                Expiration expiration = Expiration.from(expire, TimeUnit.SECONDS);
                connection.set(serializer.serialize(key), serializer.serialize(v.toString()), expiration, RedisStringCommands.SetOption.SET_IF_ABSENT);
                return true;
            }
        });
        return result;
    }

    @Override
    public boolean set(String key, Object value) {
        final StringBuffer v = new StringBuffer();
        if (value instanceof String) {
            v.append((String) value);
        } else {
            v.append(JSONUtil.toJson(value));
        }
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                connection.set(serializer.serialize(key), serializer.serialize(v.toString()));
                return true;
            }
        });
        return false;
    }

    public String get(final String key){
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] value =  connection.get(serializer.serialize(key));
                return serializer.deserialize(value);
            }
        });
        return result;
    }

    @Override
    public <T> T get(String key, Class<T> clz) {
        String result = get(key);
        return JSONUtil.toBean(result, clz);
    }

    @Override
    public boolean expire(final String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public <T> boolean setList(String key, List<T> list) {
        String value = JSONUtil.toJson(list);
        return set(key,value);
    }

    @Override
    public <T> List<T> getList(String key,Class<T> clz) {
        String json = get(key);
        if(json!=null){
            List<T> list = JSONUtil.toList(json, clz);
            return list;
        }
        return null;
    }

    @Override
    public long lpush(final String key, Object obj) {
        final String value = JSONUtil.toJson(obj);
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }

    @Override
    public long rpush(final String key, Object obj) {
        final String value = JSONUtil.toJson(obj);
        long result = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
                return count;
            }
        });
        return result;
    }

    @Override
    public String lpop(final String key) {
        String result = redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] res =  connection.lPop(serializer.serialize(key));
                return serializer.deserialize(res);
            }
        });
        return result;
    }

}
