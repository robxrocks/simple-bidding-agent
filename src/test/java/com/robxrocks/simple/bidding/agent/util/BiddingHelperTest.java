package com.robxrocks.simple.bidding.agent.util;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        testValues.put("deviceExtBrowser=Firefox", "-0.1131013246");

        List<Double> convertedValues = specUnderTest.convertCoefficients(testValues);
        assertThat(convertedValues.get(0), is(-0.1131013246));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertCoefficients_NullValue_Success() {
        Map<String, String> testValues = new HashMap<>();
        testValues.put("deviceExtBrowser=Firefox", "nil");

        specUnderTest.convertCoefficients(testValues);
    }

}