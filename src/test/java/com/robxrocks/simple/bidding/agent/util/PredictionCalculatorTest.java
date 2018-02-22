package com.robxrocks.simple.bidding.agent.util;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PredictionCalculatorTest {

    private Double deviceExtBrowserFirefox = -0.1131013246;
    private Double bannerExtSize300x250 = -0.6282185905;
    private Double deviceLanguageDe = -0.1935418172;
    private Double deviceExtTypeTablet = 0.7294739471;
    private Double bias = -6.21176449;

    private PredictionCalculator specUnderTest;

    @Before
    public void setUp() {
        specUnderTest = new PredictionCalculator();
    }

    @Test
    public void testCalculateCTR_Success() {
        List<Double> params = new ArrayList<>(Arrays.asList(
                deviceExtBrowserFirefox,
                bannerExtSize300x250,
                deviceLanguageDe,
                deviceExtTypeTablet,
                bias));
        Double result = specUnderTest.calculateCTR(params);
        assertThat(result, is(0.0016306374));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateCTRWithNullValue_Error() {
        List<Double> params = new ArrayList<>(Arrays.asList(
                null,
                bannerExtSize300x250,
                deviceLanguageDe,
                deviceExtTypeTablet,
                bias));
        specUnderTest.calculateCTR(params);
    }

}