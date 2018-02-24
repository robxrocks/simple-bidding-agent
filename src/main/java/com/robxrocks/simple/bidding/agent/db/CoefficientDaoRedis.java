package com.robxrocks.simple.bidding.agent.db;

import redis.clients.jedis.Jedis;

public class CoefficientDaoRedis extends Jedis implements CoefficientDao {

    @Override
    public String hget(String redisFieldName, String key) {
        return super.hget(redisFieldName, key);
    }
}
