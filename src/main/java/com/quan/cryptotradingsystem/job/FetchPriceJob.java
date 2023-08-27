package com.quan.cryptotradingsystem.job;

import com.quan.cryptotradingsystem.service.PriceCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FetchPriceJob {

    private final Logger logger = LoggerFactory.getLogger(FetchPriceJob.class);

    private final PriceCommandService priceCommandService;

    public FetchPriceJob(PriceCommandService priceCommandService) {
        this.priceCommandService = priceCommandService;
    }

    @Scheduled(cron = "${app.quartz.fetch-price-cron}")
    public void fetchPeriodically() {
        logger.info("Start fetching price");
        priceCommandService.fetchAndSaveLatestPrices();
        logger.info("Finish fetching price");
    }
}
