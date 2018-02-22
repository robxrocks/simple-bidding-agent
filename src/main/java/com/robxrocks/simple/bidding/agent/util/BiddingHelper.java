package com.robxrocks.simple.bidding.agent.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BiddingHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(BiddingHelper.class);

    public List<Double> convertCoefficients(Map<String, String> coefficientsMap) {
        List<Double> convertedValues = new ArrayList<>();

        coefficientsMap.forEach((key, value) -> {
            Double convertedValue;

            try {
                convertedValue = Double.valueOf(value);
            } catch (Exception ex) {
                LOGGER.error("Invalid coefficient value: {} returned for key: {}", value, key);
                throw new IllegalArgumentException("Invalid category provided for feature: " + key , ex);
            }

            convertedValues.add(convertedValue);
        });

        return convertedValues;
    }

}
