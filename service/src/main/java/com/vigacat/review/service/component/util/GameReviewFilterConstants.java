package com.vigacat.review.service.component.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public final class GameReviewFilterConstants {
    public static final int MIN_POSITIVE_SCORE = 75;
    public static final int MAX_NEGATIVE_SCORE = 45;

    public enum Types {
        POSITIVE,
        MIXED,
        NEGATIVE
    }
}
