package com.vigacat.review.dao.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;

@Data
@Builder
@Document("game_reviews")
@NoArgsConstructor
@AllArgsConstructor
public class GameReview {
    @MongoId
    private String id;
    @Field("game_id")
    private Long gameId;
    private String username;
    private LocalDate date;
    private int score;
    private String review;
}
