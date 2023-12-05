package com.vigacat.review.web.controller;

import com.vigacat.review.persistence.dto.GameReviewDto;
import com.vigacat.review.service.component.GameReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
public class GameReviewController {

    private final GameReviewService gameReviewService;

    @GetMapping
    public Set<GameReviewDto> getGameReviews(@RequestParam(name = "game_id") Long gameId) {
        return gameReviewService.getGameReviews(gameId);
    }
}
