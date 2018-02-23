package com.robxrocks.simple.bidding.agent.db;

import com.robxrocks.simple.bidding.agent.api.BiddingRequest;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class CoefficientDaoRedis extends Jedis implements CoefficientDao {
    private final String REDIS_FIELD_NAME = "model";
    private final String DEVICE_EXT_BROWSER_KEY = "deviceExtBrowser";
    private final String BANNER_EXT_SIZE_KEY = "bannerExtSize";
    private final String DEVICE_LANGUAGE_KEY = "deviceLanguage";
    private final String DEVICE_EXT_TYPE = "deviceExtType";
    private final String BIAS = "bias";

    public Map<String, String> getCoefficients(BiddingRequest biddingRequest) {
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put(DEVICE_EXT_BROWSER_KEY, biddingRequest.getDeviceExtBrowser());
        requestParams.put(BANNER_EXT_SIZE_KEY, biddingRequest.getBannerExtSize());
        requestParams.put(DEVICE_LANGUAGE_KEY, biddingRequest.getDeviceLanguage());
        requestParams.put(DEVICE_EXT_TYPE, biddingRequest.getDeviceExtType());

        Map<String, String> coefficients = new HashMap<>();
        requestParams.forEach((key, value) ->
                coefficients.put(key, super.hget(REDIS_FIELD_NAME, key + "=" + value))
        );
        coefficients.put(BIAS, super.hget(REDIS_FIELD_NAME, BIAS));
        return coefficients;
    }

}
