package com.vigacat.review.persistence.component;

import com.vigacat.review.dao.repository.GameReviewRepository;
import com.vigacat.review.persistence.dto.GameReviewDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GameReviewPersistenceImpl implements GameReviewPersistence{

    private final GameReviewRepository gameReviewRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Set<GameReviewDto> getGameReviews(Long gameId) {
        return gameReviewRepository.findAllByGameId(gameId).stream()
                .map(gameReview -> modelMapper.map(gameReview, GameReviewDto.class))
                .collect(Collectors.toSet());
    }
}
