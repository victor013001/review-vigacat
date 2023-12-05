package com.vigacat.review.web;

import com.vigacat.review.dao.config.VigacatReviewDaoConfig;
import com.vigacat.review.persistence.configuration.VigacatReviewPersistenceConfig;
import com.vigacat.review.service.config.VigacatReviewServiceConfig;
import com.vigacat.review.web.config.VigacatReviewWebConfig;
import com.vigacat.security.client.filter.configuration.ClientVigacatFilterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        VigacatReviewDaoConfig.class,
        VigacatReviewPersistenceConfig.class,
        VigacatReviewServiceConfig.class,
        VigacatReviewWebConfig.class,
        ClientVigacatFilterConfig.class
})
public class VigacatReviewApplication {
    public static void main(String... args) {
        SpringApplication.run(VigacatReviewApplication.class, args);
    }
}
