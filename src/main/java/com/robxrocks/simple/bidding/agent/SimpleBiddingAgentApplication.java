package com.robxrocks.simple.bidding.agent;

import com.robxrocks.simple.bidding.agent.db.CoefficientDao;
import com.robxrocks.simple.bidding.agent.db.CoefficientDaoRedis;
import com.robxrocks.simple.bidding.agent.resources.BiddingResource;
import com.robxrocks.simple.bidding.agent.util.BiddingHelper;
import com.robxrocks.simple.bidding.agent.util.PredictionCalculator;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import redis.clients.jedis.Jedis;

public class SimpleBiddingAgentApplication extends Application<SimpleBiddingAgentConfiguration> {

    private CoefficientDao coefficientDao;
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
        if (null == coefficientDao) {
            coefficientDao = new CoefficientDaoRedis();
        }
        if (null == biddingHelper) {
            biddingHelper = new BiddingHelper();
        }
        if (null == predictionCalculator) {
            predictionCalculator = new PredictionCalculator();
        }

        environment.jersey().register(BiddingResource.builder()
                .coefficientDao(coefficientDao)
                .biddingHelper(biddingHelper)
                .predictionCalculator(predictionCalculator)
                .build());
    }

}
