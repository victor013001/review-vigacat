package com.vigacat.review.persistence.component;

import com.vigacat.review.dao.document.GameReview;
import com.vigacat.review.dao.repository.GameReviewRepository;
import com.vigacat.review.persistence.dto.GameReviewDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class GameReviewPersistenceImplTest {

    @InjectMocks
    private GameReviewPersistenceImpl gameReviewPersistence;
    @Mock
    private GameReviewRepository gameReviewRepository;
    @Spy
    private ModelMapper modelMapper;

    @Test
    public void getGameReviews() {
        Long gameDestinyId = 1L;
        int destinyReviewScore = 100;
        String destinyReview = "This game is amazing!";
        LocalDate currentDate = LocalDate.now();
        String userVictor = "victorm.osorio";
        String userAdmin = "admin";

        GameReview destinyReviewUserVictor = GameReview.builder()
                .gameId(gameDestinyId)
                .score(destinyReviewScore)
                .review(destinyReview)
                .username(userVictor)
                .date(currentDate)
                .build();

        GameReview destinyReviewAdmin = GameReview.builder()
                .gameId(gameDestinyId)
                .score(destinyReviewScore)
                .review(destinyReview)
                .username(userAdmin)
                .date(currentDate)
                .build();

        List<GameReview> destinyReviews = List.of(destinyReviewUserVictor, destinyReviewAdmin);

        Mockito.when(gameReviewRepository.findAllByGameId(gameDestinyId))
                .thenReturn(destinyReviews);

        final Set<GameReviewDto> destinyReviewDtos = gameReviewPersistence.getGameReviews(gameDestinyId);

        Mockito.verify(gameReviewRepository)
                .findAllByGameId(gameDestinyId);

        Assertions.assertThat(destinyReviewDtos)
                .extracting(GameReviewDto::getGameId)
                .contains(gameDestinyId);

        Assertions.assertThat(destinyReviewDtos)
                .extracting(GameReviewDto::getUsername)
                .contains(userVictor, userAdmin);
    }

    @Test
    public void saveGameReview() {
        Long gameDestinyId = 1L;
        int destinyReviewScore = 100;
        String destinyReview = "This game is amazing!";
        LocalDate currentDate = LocalDate.now();
        String userVictor = "victorm.osorio";
        String destinyReviewId = "123456789";

        GameReviewDto destinyReviewUserVictorDto = GameReviewDto.builder()
                .gameId(gameDestinyId)
                .score(destinyReviewScore)
                .review(destinyReview)
                .username(userVictor)
                .date(currentDate)
                .build();

        Mockito.when(gameReviewRepository.save(Mockito.any(GameReview.class)))
                .then((Answer<GameReview>) invocationOnMock -> {
                    GameReview destinyGameReviewSaved = invocationOnMock.getArgument(0);
                    destinyGameReviewSaved.setId(destinyReviewId);
                    return destinyGameReviewSaved;
                });

        GameReviewDto gameReviewDtoResponse = gameReviewPersistence.saveGameReview(destinyReviewUserVictorDto);

        Mockito.verify(gameReviewRepository)
                        .save(Mockito.any(GameReview.class));

        Assertions.assertThat(gameReviewDtoResponse)
                .hasFieldOrPropertyWithValue("id", destinyReviewId)
                .hasFieldOrPropertyWithValue("gameId", gameDestinyId)
                .hasFieldOrPropertyWithValue("username", userVictor)
                .hasFieldOrPropertyWithValue("date", currentDate)
                .hasFieldOrPropertyWithValue("score", destinyReviewScore)
                .hasFieldOrPropertyWithValue("review", destinyReview);
    }

    @Test
    public void userGameIdReviewExist() {
        String userVictor = "victorm.osorio";
        Long gameDestinyId = 1L;
        boolean gameExist = true;

        Mockito.when(gameReviewRepository.findReviewByUsernameAndGameId(userVictor, gameDestinyId))
                .thenReturn(gameExist);

        boolean gameExistResponse = gameReviewPersistence.userGameIdReviewExist(userVictor, gameDestinyId);

        Mockito.verify(gameReviewRepository)
                        .findReviewByUsernameAndGameId(userVictor, gameDestinyId);

        Assertions.assertThat(gameExistResponse).isTrue();
    }

}
