package com.robxrocks.simple.bidding.agent.util;

import com.robxrocks.simple.bidding.agent.db.CoefficientDao;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.robxrocks.simple.bidding.agent.BiddingFixtures.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class CoefficientDaoClientTest {

    private CoefficientDaoClient specUnderTest;
    private CoefficientDao coefficientDao;
    
    @Before
    public void setUp() {
        coefficientDao = mock(CoefficientDao.class);
        specUnderTest = new CoefficientDaoClient(coefficientDao);

        when(coefficientDao
                .hget(REDIS_FIELD_NAME, concatKeyValueForRedis(DEVICE_EXT_BROWSER_KEY, DEVICE_EXT_BROWSER_VALUE)))
                .thenReturn(DEVICE_EXT_BROWSER_COEFFICIENT);
        when(coefficientDao
                .hget(REDIS_FIELD_NAME, concatKeyValueForRedis(BANNER_EXT_SIZE_KEY, BANNER_EXT_SIZE_VALUE)))
                .thenReturn(BANNER_EXT_SIZE_COEFFICIENT);
        when(coefficientDao
                .hget(REDIS_FIELD_NAME, concatKeyValueForRedis(DEVICE_LANGUAGE_KEY, DEVICE_LANGUAGE_VALUE)))
                .thenReturn(DEVICE_LANGUAGE_COEFFICIENT);
        when(coefficientDao
                .hget(REDIS_FIELD_NAME, concatKeyValueForRedis(DEVICE_EXT_TYPE_KEY, DEVICE_EXT_TYPE_VALUE)))
                .thenReturn(DEVICE_EXT_TYPE_COEFFICIENT);
        when(coefficientDao.hget(REDIS_FIELD_NAME, BIAS)).thenReturn(BIAS_COEFFICIENT);

    }

    @Test
    public void testGetCoefficientsSuccess() {
        Map<String, String> coefficients = specUnderTest.getCoefficients(
                DEVICE_EXT_BROWSER_VALUE,
                BANNER_EXT_SIZE_VALUE,
                DEVICE_LANGUAGE_VALUE,
                DEVICE_EXT_TYPE_VALUE);

        verify(coefficientDao, times(5)).hget(anyString(), anyString());
        assertThat(coefficients.get(DEVICE_EXT_BROWSER_KEY), is(DEVICE_EXT_BROWSER_COEFFICIENT));
        assertThat(coefficients.get(BANNER_EXT_SIZE_KEY), is(BANNER_EXT_SIZE_COEFFICIENT));
        assertThat(coefficients.get(DEVICE_LANGUAGE_KEY), is(DEVICE_LANGUAGE_COEFFICIENT));
        assertThat(coefficients.get(DEVICE_EXT_TYPE_KEY), is(DEVICE_EXT_TYPE_COEFFICIENT));
        assertThat(coefficients.get(BIAS), is(BIAS_COEFFICIENT));
    }

}