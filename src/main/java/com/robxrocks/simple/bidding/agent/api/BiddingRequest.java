package com.robxrocks.simple.bidding.agent.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class BiddingRequest {

    @NotNull
    @NotBlank
    private final String deviceExtBrowser;

    @NotNull
    @NotBlank
    private final String bannerExtSize;

    @NotNull
    @NotBlank
    private final String deviceLanguage;

    @NotNull
    @NotBlank
    private final String deviceExtType;

}
