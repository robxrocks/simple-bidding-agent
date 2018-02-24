package com.robxrocks.simple.bidding.agent;

import com.robxrocks.simple.bidding.agent.api.BiddingRequest;

public class BiddingFixtures {

    public static final String REDIS_FIELD_NAME = "model";
    public static final String BIAS = "bias";
    public static final String DEVICE_EXT_BROWSER_KEY = "deviceExtBrowser";
    public static final String BANNER_EXT_SIZE_KEY = "bannerExtSize";
    public static final String DEVICE_LANGUAGE_KEY = "deviceLanguage";
    public static final String DEVICE_EXT_TYPE_KEY = "deviceExtType";

    public static final String DEVICE_EXT_BROWSER_VALUE = "Firefox";
    public static final String BANNER_EXT_SIZE_VALUE = "300x250";
    public static final String DEVICE_LANGUAGE_VALUE = "De";
    public static final String DEVICE_EXT_TYPE_VALUE = "Tablet";

    public static final String DEVICE_EXT_BROWSER_COEFFICIENT = "-0.1131013246";
    public static final String BANNER_EXT_SIZE_COEFFICIENT = "-0.6282185905";
    public static final String DEVICE_LANGUAGE_COEFFICIENT = "-0.1935418172";
    public static final String DEVICE_EXT_TYPE_COEFFICIENT = "0.7294739471";
    public static final String BIAS_COEFFICIENT = "-6.21176449";

    public static BiddingRequest getBiddingRequest() {
        return BiddingRequest.builder()
                .deviceExtBrowser(DEVICE_EXT_BROWSER_VALUE)
                .bannerExtSize(BANNER_EXT_SIZE_VALUE)
                .deviceLanguage(DEVICE_LANGUAGE_VALUE)
                .deviceExtType(DEVICE_EXT_TYPE_VALUE)
                .build();
    }

    public static String concatKeyValueForRedis(String key, String value) {
        return key + "=" + value;
    }
}
