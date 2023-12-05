package com.vigacat.review.persistence.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.vigacat.review.persistence.component"})
@Import(ModelMapperConfiguration.class)
public class VigacatReviewPersistenceConfig {
}
