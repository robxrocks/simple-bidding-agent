package com.robxrocks.simple.bidding.agent.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BiddingResponse {

    private final String ctr;
}
