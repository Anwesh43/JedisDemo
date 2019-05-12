package com.demos.jedisdemo;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

public class RedisDao {

    private Jedis jedis = new Jedis();

    public void setKey(String keyName, String value) {
        jedis.set(keyName, value);
    }

    public String getValue(String keyName) {
        return jedis.get(keyName);
    }

    public void setHashValue(String key, String field, String value) {
        jedis.hset(key, field, value);
    }

    public String getHashValue(String key, String field) {
        return jedis.hget(key, field);
    }

    public Map<String, String> getAllHashValues(String key) {
        return jedis.hgetAll(key);
    }

    public void mapToRedisHash(String mapName, Map<String, String> keyValueMap) {
        keyValueMap.forEach((k, v) -> {
            setHashValue(mapName, k, v);
        });
    }

    public void addToList(String listName, String item) {
        jedis.rpush(listName, item);
    }

    public List<String> getList(String listName) {
        return jedis.lrange(listName, 0, -1);
    }

    public void addListToRedisList(String listName, List<String> items) {
        items.forEach(item -> {
           addToList(listName, item);
        });
    }
}
