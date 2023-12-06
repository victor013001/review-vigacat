package com.vigaca.review.service.component;

import com.vigacat.review.persistence.component.GameReviewPersistence;
import com.vigacat.review.persistence.dto.GameReviewDto;
import com.vigacat.review.service.component.GameReviewServiceImpl;
import com.vigacat.review.service.component.security.util.VigacatSecurityContext;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.time.LocalDate;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class GameReviewServiceImplTest {
    @InjectMocks
    private GameReviewServiceImpl gameReviewService;
    @Mock
    private GameReviewPersistence gameReviewPersistence;
    @Mock
    private VigacatSecurityContext vigacatSecurityContext;

    @Test
    public void getGameReviews() {
        Long destinyGameId = 1L;
        int destinyReviewScore = 100;
        String destinyReview = "This game is amazing!";
        LocalDate currentDate = LocalDate.now();
        String userVictor = "victorm.osorio";
        String userAdmin = "admin";

        GameReviewDto destinyReviewDtoUserVictor = GameReviewDto.builder()
                .gameId(destinyGameId)
                .score(destinyReviewScore)
                .review(destinyReview)
                .username(userVictor)
                .date(currentDate)
                .build();

        GameReviewDto destinyReviewDtoAdmin = GameReviewDto.builder()
                .gameId(destinyGameId)
                .score(destinyReviewScore)
                .review(destinyReview)
                .username(userAdmin)
                .date(currentDate)
                .build();

        Set<GameReviewDto> destinyReviewsDto = Set.of(destinyReviewDtoUserVictor, destinyReviewDtoAdmin);

        Mockito.when(gameReviewPersistence.getGameReviews(destinyGameId))
                .thenReturn(destinyReviewsDto);

        final Set<GameReviewDto> destinyReviewsDtoResponse = gameReviewService.getGameReviews(destinyGameId);

        Mockito.verify(gameReviewPersistence)
                .getGameReviews(destinyGameId);

        Assertions.assertThat(destinyReviewsDtoResponse)
                .extracting(GameReviewDto::getGameId)
                .contains(destinyGameId);
    }

    @Test
    public void createGameReview() {
        Long destinyGameId = 1L;
        int destinyReviewScore = 100;
        String destinyReview = "This game is amazing!";
        String userVictor = "victorm.osorio";

        Mockito.when(vigacatSecurityContext.getUsernameAuthenticated())
                .thenReturn(userVictor);

        Mockito.when(gameReviewPersistence.userGameIdReviewExist(userVictor, destinyGameId))
                        .thenReturn(false);

        Mockito.when(gameReviewPersistence.saveGameReview(Mockito.any(GameReviewDto.class)))
                        .then((Answer<GameReviewDto>) invocationOnMock -> invocationOnMock.getArgument(0));

        final GameReviewDto destinyGameReviewDto = gameReviewService.createGameReview(destinyGameId, destinyReviewScore, destinyReview);

        Mockito.verify(vigacatSecurityContext)
                .getUsernameAuthenticated();

        Mockito.verify(gameReviewPersistence)
                .userGameIdReviewExist(userVictor, destinyGameId);

        Mockito.verify(gameReviewPersistence)
                .saveGameReview(Mockito.any(GameReviewDto.class));

        Assertions.assertThat(destinyGameReviewDto)
                .hasFieldOrPropertyWithValue("gameId", destinyGameId)
                .hasFieldOrPropertyWithValue("username", userVictor)
                .hasFieldOrPropertyWithValue("date", LocalDate.now())
                .hasFieldOrPropertyWithValue("score", destinyReviewScore)
                .hasFieldOrPropertyWithValue("review", destinyReview);
    }

}
