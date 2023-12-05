package com.vigacat.review.dao.repository;

import com.vigacat.review.dao.document.GameReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GameReviewRepository extends MongoRepository<GameReview, String> {

    @Query("{ 'game_id' : ?0 }")
    List<GameReview> findAllByGameId(Long gameId);
}
