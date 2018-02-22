package com.robxrocks.simple.bidding.agent.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PredictionCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredictionCalculator.class);

    public Double calculateCTR(List<Double> params) {
        if (params.contains(null)) {
            LOGGER.error("One or more coefficients are null value(s)");
            throw new IllegalArgumentException("One or more coefficients are null value(s)");
        }

        Double x = params.stream().mapToDouble(Double::doubleValue).sum();
        Double result = 1.0 / (1.0 + Math.exp(-x));
        return (long) (result * 1e10) / 1e10;
    }
}
