package com.robxrocks.simple.bidding.agent.resources;

import com.robxrocks.simple.bidding.agent.util.BiddingHelper;
import com.robxrocks.simple.bidding.agent.util.CoefficientDaoClient;
import com.robxrocks.simple.bidding.agent.util.PredictionCalculator;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;

import static com.robxrocks.simple.bidding.agent.BiddingFixtures.getBiddingRequest;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class BiddingResourceTest {

    private BiddingResource resource;
    private CoefficientDaoClient coefficientDaoClient;
    private BiddingHelper biddingHelper;
    private PredictionCalculator predictionCalculator;

    @Before
    public void setUp() {
        coefficientDaoClient = mock(CoefficientDaoClient.class);
        biddingHelper = mock(BiddingHelper.class);
        predictionCalculator = mock(PredictionCalculator.class);

        resource = BiddingResource.builder()
                .coefficientDaoClient(coefficientDaoClient)
                .biddingHelper(biddingHelper)
                .predictionCalculator(predictionCalculator)
                .build();

        when(coefficientDaoClient.getCoefficients(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Collections.emptyMap());
        when(biddingHelper.convertCoefficients(anyMap())).thenReturn(Arrays.asList(1.0));
        when(predictionCalculator.calculateCTR(anyList())).thenReturn(1.0);
    }

    @Test
    public void testPredictCRT_Success() {
        Response response = resource.predictCTR(getBiddingRequest());

        verify(coefficientDaoClient, times(1))
                .getCoefficients(anyString(), anyString(), anyString(), anyString());
        verify(biddingHelper, times(1)).convertCoefficients(anyMap());
        verify(predictionCalculator, times(1)).calculateCTR(anyList());
        assertThat(response.getStatus(), is(200));
    }

}
