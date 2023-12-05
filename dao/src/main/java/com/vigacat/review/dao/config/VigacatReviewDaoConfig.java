package com.vigacat.review.dao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EntityScan(basePackages = {"com.vigacat.review.dao.entity"})
@EnableMongoRepositories(basePackages = {"com.vigacat.review.dao.repository"})
public class VigacatReviewDaoConfig {
}
