package com.vigacat.review.service.component;

import com.vigacat.review.persistence.dto.GameReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FilterGameReviewService {

    private final List<FilterGameReview> filterGameReviews;

    public Set<GameReviewDto> getReviews(Long gameId, String filterType){
        return getImplementation(filterType).getFilteredGameReview(gameId);
    }

    private FilterGameReview getImplementation(String filterType) {
        return filterGameReviews.stream()
                .filter(service -> filterType.equals(service.getFilterType().toString()))
                .findFirst()
                .orElseThrow();
    }
}
