package com.robxrocks.simple.bidding.agent.util;

import com.robxrocks.simple.bidding.agent.db.CoefficientDao;

import java.util.HashMap;
import java.util.Map;

public class CoefficientDaoClient {

    private CoefficientDao dao;
    
    public CoefficientDaoClient(CoefficientDao dao) {
        this.dao = dao;
    }

    public Map<String, String> getCoefficients(String deviceExtBrowser,
                                               String bannerExtSize,
                                               String deviceLanguage,
                                               String deviceExtType) {
        final String REDIS_FIELD_NAME = "model";
        final String DEVICE_EXT_BROWSER_KEY = "deviceExtBrowser";
        final String BANNER_EXT_SIZE_KEY = "bannerExtSize";
        final String DEVICE_LANGUAGE_KEY = "deviceLanguage";
        final String DEVICE_EXT_TYPE = "deviceExtType";
        final String BIAS = "bias";
        
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put(DEVICE_EXT_BROWSER_KEY, deviceExtBrowser);
        requestParams.put(BANNER_EXT_SIZE_KEY, bannerExtSize);
        requestParams.put(DEVICE_LANGUAGE_KEY, deviceLanguage);
        requestParams.put(DEVICE_EXT_TYPE, deviceExtType);

        Map<String, String> coefficients = new HashMap<>();
        requestParams.forEach((key, value) ->
                coefficients.put(key, dao.hget(REDIS_FIELD_NAME, key + "=" + value))
        );
        coefficients.put(BIAS, dao.hget(REDIS_FIELD_NAME, BIAS));
        return coefficients;
    }

}
