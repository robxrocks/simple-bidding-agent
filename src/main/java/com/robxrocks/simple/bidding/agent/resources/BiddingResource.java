package com.robxrocks.simple.bidding.agent.resources;

import com.robxrocks.simple.bidding.agent.api.BiddingRequest;
import com.robxrocks.simple.bidding.agent.api.BiddingResponse;
import com.robxrocks.simple.bidding.agent.db.CoefficientDao;
import com.robxrocks.simple.bidding.agent.util.BiddingHelper;
import com.robxrocks.simple.bidding.agent.util.PredictionCalculator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/")
@Api("/")
@Produces(APPLICATION_JSON)
@Builder
public class BiddingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(BiddingResource.class);

    @NotNull
    private CoefficientDao coefficientDao;

    @NotNull
    private BiddingHelper biddingHelper;

    @NotNull
    private PredictionCalculator predictionCalculator;

    @POST
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Predicts a click through rate (CTR)", notes = "Predicts a click through rate for given features")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Valid response - CTR returned"),
            @ApiResponse(code = 400, message = "Invalid request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Response predictCTR(@ApiParam(value = "Bidding Request", required = true)
                               @Valid final BiddingRequest biddingRequest) {
        String ctrPrediction;

        try {
            Map<String, String> coefficients = coefficientDao.getCoefficients(biddingRequest);

            List<Double> convertedCoefficients = biddingHelper.convertCoefficients(coefficients);

            ctrPrediction = predictionCalculator.calculateCTR(convertedCoefficients).toString();

        } catch (Exception ex) {
            LOGGER.error("CTR could not be predicted due to unexpected exception: {}", ex.getMessage());
            if (ex instanceof IllegalArgumentException) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            } else {
                throw ex;
            }
        }

        return Response.ok(BiddingResponse.builder().ctr(ctrPrediction).build()).build();
    }

}
