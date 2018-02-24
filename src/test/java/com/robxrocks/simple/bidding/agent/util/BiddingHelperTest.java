package com.robxrocks.simple.bidding.agent.util;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.robxrocks.simple.bidding.agent.BiddingFixtures.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BiddingHelperTest {

    private BiddingHelper specUnderTest;

    @Before
    public void setUp() {
        specUnderTest = new BiddingHelper();
    }

    @Test
    public void testConvertCoefficients_Success() {
        Map<String, String> testValues = new HashMap<>();
        testValues.put(concatKeyValueForRedis(DEVICE_EXT_BROWSER_KEY, DEVICE_EXT_BROWSER_VALUE),
                DEVICE_EXT_BROWSER_COEFFICIENT);

        List<Double> convertedValues = specUnderTest.convertCoefficients(testValues);
        assertThat(convertedValues.get(0), is(Double.valueOf(DEVICE_EXT_BROWSER_COEFFICIENT)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertCoefficients_NullValue_Success() {
        Map<String, String> testValues = new HashMap<>();
        testValues.put(concatKeyValueForRedis(DEVICE_EXT_BROWSER_KEY, DEVICE_EXT_BROWSER_VALUE), "nil");

        specUnderTest.convertCoefficients(testValues);
    }

}