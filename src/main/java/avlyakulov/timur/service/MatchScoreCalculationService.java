package avlyakulov.timur.service;

import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchesInProgress;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class MatchScoreCalculationService {

    public void addPointToWinnerOfGame(int winnerId, UUID matchId) {
        Match match = MatchesInProgress.getMatchById(matchId);
        switch (match.getState()) {
            case GAME -> {
                if (match.getPlayerOne().getId() == winnerId) {
                    match.setPointPlayerOne(increasePointPlayerInGame(match.getPointPlayerOne()));
                } else {
                    match.setPointPlayerTwo(increasePointPlayerInGame(match.getPointPlayerTwo()));
                }
            }
            case ADVANTAGE -> {

            }
            case TIE -> {

            }
            case FININSHED -> {

            }
            default -> throw new RuntimeException("Something went wrong in addPointToWinnerOfGame()");
        }
        MatchesInProgress.updateMatch(matchId, match);
    }

    private int increasePointPlayerInGame(int playerPoint) {
        if (playerPoint < 30) {
            return playerPoint + 15;
        } else {
            return playerPoint + 10;
        }
    }

//    private void
}