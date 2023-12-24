package com.vigacat.review.service.component;

import com.vigacat.review.persistence.dto.GameReviewDto;
import com.vigacat.review.service.component.util.GameReviewFilterConstants.Types;

import java.util.Set;

public interface FilterGameReview {

    Types getFilterType();

    Set<GameReviewDto> getFilteredGameReview(Long gameId);
}
