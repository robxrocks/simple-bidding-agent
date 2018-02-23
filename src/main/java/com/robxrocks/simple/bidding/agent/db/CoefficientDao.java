package com.robxrocks.simple.bidding.agent.db;

import com.robxrocks.simple.bidding.agent.api.BiddingRequest;

import java.util.Map;

public interface CoefficientDao {

    Map<String, String> getCoefficients(BiddingRequest request);

}
