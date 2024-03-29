package com.vigacat.review.dao.repository;

import com.vigacat.review.dao.document.GameReview;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GameReviewRepository extends MongoRepository<GameReview, String> {

    @Query("{ 'game_id' : ?0 }")
    List<GameReview> findAllByGameId(Long gameId);

    @Query(value = "{ 'username' : ?0,  'game_id' : ?1 }", exists = true)
    boolean findReviewByUsernameAndGameId(String username, Long gameId);

    @Query("{ 'game_id': ?0, 'score': { $gte: ?1 } }")
    List<GameReview> findAllByGameIdAndPositiveScore(Long gameId, int minPositiveScore);

    @Query("{ 'game_id': ?0, $and: [{ 'score': { $gt: ?1 } }, { 'score': { $lt: ?2 } }] }")
    List<GameReview> findAllByGameIdAndMixedScore(Long gameId, int maxNegativeScore, int minPositiveScore);

    @Query("{ 'game_id': ?0, 'score': { $lte: ?1 } }")
    List<GameReview> findAllByGameIdAndNegativeScore(Long gameId, int maxNegativeScore);
}
