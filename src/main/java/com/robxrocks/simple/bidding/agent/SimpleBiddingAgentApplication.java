package com.robxrocks.simple.bidding.agent;

import com.robxrocks.simple.bidding.agent.db.CoefficientDao;
import com.robxrocks.simple.bidding.agent.db.CoefficientDaoRedis;
import com.robxrocks.simple.bidding.agent.resources.BiddingResource;
import com.robxrocks.simple.bidding.agent.util.BiddingHelper;
import com.robxrocks.simple.bidding.agent.util.CoefficientDaoClient;
import com.robxrocks.simple.bidding.agent.util.PredictionCalculator;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

public class SimpleBiddingAgentApplication extends Application<SimpleBiddingAgentConfiguration> {

    private CoefficientDaoClient coefficientDaoClient;
    private BiddingHelper biddingHelper;
    private PredictionCalculator predictionCalculator;

    public static void main(final String[] args) throws Exception {
        new SimpleBiddingAgentApplication().run(args);
    }

    @Override
    public String getName() {
        return "SimpleBiddingAgent";
    }

    @Override
    public void initialize(final Bootstrap<SimpleBiddingAgentConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<SimpleBiddingAgentConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(SimpleBiddingAgentConfiguration configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }

    @Override
    public void run(final SimpleBiddingAgentConfiguration configuration,
                    final Environment environment) {
        if (null == coefficientDaoClient) {
            CoefficientDao coefficientDao = new CoefficientDaoRedis();
            coefficientDaoClient = new CoefficientDaoClient(coefficientDao);
        }
        if (null == biddingHelper) {
            biddingHelper = new BiddingHelper();
        }
        if (null == predictionCalculator) {
            predictionCalculator = new PredictionCalculator();
        }

        environment.jersey().register(BiddingResource.builder()
                .coefficientDaoClient(coefficientDaoClient)
                .biddingHelper(biddingHelper)
                .predictionCalculator(predictionCalculator)
                .build());
    }

}
