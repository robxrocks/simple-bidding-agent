package com.robxrocks.simple.bidding.agent.util;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.robxrocks.simple.bidding.agent.BiddingFixtures.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PredictionCalculatorTest {

    private PredictionCalculator specUnderTest;

    @Before
    public void setUp() {
        specUnderTest = new PredictionCalculator();
    }

    @Test
    public void testCalculateCTR_Success() {
        Double expectedCTR = 0.0016306374;
        List<Double> params = new ArrayList<>(Arrays.asList(
                Double.valueOf(DEVICE_EXT_BROWSER_COEFFICIENT),
                Double.valueOf(BANNER_EXT_SIZE_COEFFICIENT),
                Double.valueOf(DEVICE_LANGUAGE_COEFFICIENT),
                Double.valueOf(DEVICE_EXT_TYPE_COEFFICIENT),
                Double.valueOf(BIAS_COEFFICIENT)));
        Double result = specUnderTest.calculateCTR(params);
        assertThat(result, is(expectedCTR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateCTRWithNullValue_Error() {
        List<Double> params = new ArrayList<>(Arrays.asList(
                null,
                Double.valueOf(BANNER_EXT_SIZE_COEFFICIENT),
                Double.valueOf(DEVICE_LANGUAGE_COEFFICIENT),
                Double.valueOf(DEVICE_EXT_TYPE_COEFFICIENT),
                Double.valueOf(BIAS_COEFFICIENT)));
        specUnderTest.calculateCTR(params);
    }

}
