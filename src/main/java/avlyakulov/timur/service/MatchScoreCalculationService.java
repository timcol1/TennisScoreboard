package avlyakulov.timur.service;

import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchesInProgress;
import avlyakulov.timur.model.State;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class MatchScoreCalculationService {

    public void addPointToWinnerOfGame(int winnerId, UUID matchId) {
        Match match = MatchesInProgress.getMatchById(matchId);
        if (match.getState().equals(State.GAME)) {
            if (match.getPlayerOneId() == winnerId) {
                match.setPointPlayerOne(increasePointPlayerInGame(match.getPointPlayerOne()));
            } else {
                match.setPointPlayerTwo(increasePointPlayerInGame(match.getPointPlayerTwo()));
            }
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
}