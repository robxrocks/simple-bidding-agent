package com.robxrocks.simple.bidding.agent.db;

public interface CoefficientDao {

    String hget(String redisFieldName, String key);

}
