package com.vigacat.review.dao.document;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Builder
@Document("game_reviews")
public class GameReview {
    @Id
    private String id;
    @Field("game_id")
    private Long gameId;
    private String username;
    private Date date;
    private int score;
    private String review;
}
