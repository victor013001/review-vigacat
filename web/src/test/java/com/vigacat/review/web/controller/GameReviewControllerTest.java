package com.vigacat.review.web.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vigacat.review.persistence.dto.GameReviewDto;
import com.vigacat.review.service.component.GameReviewService;
import com.vigacat.review.web.dto.GameReviewRequest;
import com.vigacat.review.web.dto.GameReviewUpdateRequest;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class GameReviewControllerTest {

    @InjectMocks
    private GameReviewController gameReviewController;
    @Mock
    private GameReviewService gameReviewService;
    private MockMvc mockMvc;

    @Before
    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameReviewController).build();
    }

    @Test
    public void getGameReviews() throws Exception {
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

        Set<GameReviewDto> destinyReviewsDto = new LinkedHashSet<>();
        destinyReviewsDto.add(destinyReviewDtoUserVictor);
        destinyReviewsDto.add(destinyReviewDtoAdmin);

        Mockito.when(gameReviewService.getGameReviews(destinyGameId))
                .thenReturn(destinyReviewsDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/game")
                        .param("game_id", String.valueOf(destinyGameId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].gameId").value(destinyGameId))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value(Matchers.is(userVictor)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date[0]").value(Matchers.is(currentDate.getYear())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date[1]").value(Matchers.is(currentDate.getMonthValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].date[2]").value(Matchers.is(currentDate.getDayOfMonth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].score").value(Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].review").value(Matchers.is(destinyReview))).andExpect(MockMvcResultMatchers.jsonPath("$[0].gameId").value(destinyGameId))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].username").value(Matchers.is(userAdmin)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].date[0]").value(Matchers.is(currentDate.getYear())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].date[1]").value(Matchers.is(currentDate.getMonthValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].date[2]").value(Matchers.is(currentDate.getDayOfMonth())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].score").value(Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].review").value(Matchers.is(destinyReview)))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(gameReviewService)
                .getGameReviews(destinyGameId);
    }

    @Test
    public void createGameReview() throws Exception {
        Long destinyGameId = 1L;
        int destinyReviewScore = 100;
        String destinyReview = "This game is amazing!";
        LocalDate currentDate = LocalDate.now();
        String userVictor = "victorm.osorio";

        GameReviewRequest gameDestinyReviewRequest = GameReviewRequest.builder()
                .gameId(destinyGameId)
                .score(destinyReviewScore)
                .review(destinyReview)
                .build();

        String gameDestinyReviewRequestJson = new ObjectMapper()
                .writeValueAsString(gameDestinyReviewRequest);

        GameReviewDto destinyReviewDtoUserVictor = GameReviewDto.builder()
                .gameId(destinyGameId)
                .score(destinyReviewScore)
                .review(destinyReview)
                .username(userVictor)
                .date(currentDate)
                .build();

        Mockito.when(gameReviewService.createGameReview(destinyGameId, destinyReviewScore, destinyReview))
                .thenReturn(destinyReviewDtoUserVictor);

        mockMvc.perform(MockMvcRequestBuilders.post("/game")
                        .content(gameDestinyReviewRequestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gameId").value(destinyGameId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(Matchers.is(userVictor)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.score").value(Matchers.is(destinyReviewScore)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.review").value(Matchers.is(destinyReview)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date[0]").value(Matchers.is(currentDate.getYear())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date[1]").value(Matchers.is(currentDate.getMonthValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date[2]").value(Matchers.is(currentDate.getDayOfMonth())))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(gameReviewService)
                .createGameReview(destinyGameId, destinyReviewScore, destinyReview);
    }

    @Test
    public void updateGameReviewTest() throws Exception {
        final String existingReviewId = "1";
        final int updatedScore = 100;
        final String updatedReviewText = "This game is not amazing!";
        givenExistingGameReviewWithIdAndUpdatedScoreAndReview(existingReviewId, updatedScore, updatedReviewText);
        ResultActions updateGameReviewResultActions = whenCallPatchGameReviewByIdWithScoreAndReview(existingReviewId, updatedScore, updatedReviewText);
        thenGameReviewScoreAndReviewTextIsSuccessfullyUpdated(updateGameReviewResultActions, existingReviewId, updatedScore, updatedReviewText);
    }

    private void givenExistingGameReviewWithIdAndUpdatedScoreAndReview(String existingReviewId, int updatedScore, String updatedReviewText) {
        final long gameId = 1L;
        final String usernameVictor = "victorm.osorio";
        final LocalDate currentDate = LocalDate.now();
        final GameReviewDto updatedGameReviewDto = GameReviewDto.builder()
                .id(existingReviewId)
                .gameId(gameId)
                .username(usernameVictor)
                .score(updatedScore)
                .review(updatedReviewText)
                .date(currentDate)
                .build();

        BDDMockito.given(gameReviewService.updateGameReview(
                existingReviewId,
                updatedScore,
                updatedReviewText
        )).willReturn(updatedGameReviewDto);
    }

    private ResultActions whenCallPatchGameReviewByIdWithScoreAndReview(String existingReviewId, int updatedScore, String updatedReviewText) throws Exception {

        final GameReviewUpdateRequest gameReviewUpdateRequest = GameReviewUpdateRequest.builder()
                .score(updatedScore)
                .review(updatedReviewText)
                .build();

        final String gameReviewUpdatedRequestJson = new ObjectMapper()
                .writeValueAsString(gameReviewUpdateRequest);

        return mockMvc.perform(MockMvcRequestBuilders.patch("/game")
                .param("review_id", existingReviewId)
                .content(gameReviewUpdatedRequestJson)
                .contentType(MediaType.APPLICATION_JSON));
    }

    private void thenGameReviewScoreAndReviewTextIsSuccessfullyUpdated(ResultActions updateGameReviewResultActions, String existingReviewId, int updatedScore, String updatedReviewText) throws Exception {
        updateGameReviewResultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Matchers.is(existingReviewId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.score").value(updatedScore))
                .andExpect(MockMvcResultMatchers.jsonPath("$.review").value(Matchers.is(updatedReviewText)))
                .andDo(MockMvcResultHandlers.print());
    }
}
