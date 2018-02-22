package com.robxrocks.simple.bidding.agent.resources;

import com.robxrocks.simple.bidding.agent.api.BiddingRequest;
import com.robxrocks.simple.bidding.agent.db.CoefficientDao;
import com.robxrocks.simple.bidding.agent.util.BiddingHelper;
import com.robxrocks.simple.bidding.agent.util.PredictionCalculator;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class BiddingResourceTest {

    private BiddingResource resource;
    private CoefficientDao coefficientDao;
    private BiddingHelper biddingHelper;
    private PredictionCalculator predictionCalculator;

    @Before
    public void setUp() {
        coefficientDao = mock(CoefficientDao.class);
        biddingHelper = mock(BiddingHelper.class);
        predictionCalculator = mock(PredictionCalculator.class);

        resource = BiddingResource.builder()
                .coefficientDao(coefficientDao)
                .biddingHelper(biddingHelper)
                .predictionCalculator(predictionCalculator)
                .build();

        when(coefficientDao.hget(anyString(), anyString())).thenReturn("1.0");
        when(biddingHelper.convertCoefficients(anyMap())).thenReturn(Arrays.asList(1.0));
        when(predictionCalculator.calculateCTR(anyList())).thenReturn(1.0);
    }

    @Test
    public void testPredictCRT_Success() {
        final BiddingRequest request = BiddingRequest.builder()
                .bannerExtSize("test")
                .deviceExtBrowser("test")
                .deviceExtType("test")
                .deviceLanguage("test")
                .build();

        Response response = resource.predictCTR(request);

        verify(coefficientDao, times(5)).hget(anyString(),anyString());
        verify(biddingHelper, times(1)).convertCoefficients(anyMap());
        verify(predictionCalculator, times(1)).calculateCTR(anyList());
        assertThat(response.getStatus(), is(200));
    }

}
