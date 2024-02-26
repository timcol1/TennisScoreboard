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
        switch (match.getState()) {
            case GAME -> {
                if (match.getPlayerOne().getId() == winnerId) {
                    match.setPointPlayerOne(increasePointPlayerInGame(match.getPointPlayerOne()));
                } else {
                    match.setPointPlayerTwo(increasePointPlayerInGame(match.getPointPlayerTwo()));
                }
            }
            case ADVANTAGE -> {
                if (match.getPlayerOne().getId() == winnerId) {
                    match.setPointPlayerOne(increasePointPlayerInAdvantage(match.getPointPlayerOne()));
                } else {
                    match.setPointPlayerTwo(increasePointPlayerInAdvantage(match.getPointPlayerTwo()));
                }
            }
            case TIE -> {
                if (match.getPlayerOne().getId() == winnerId) {
                    match.setPointPlayerOne(increasePointPlayerInTie(match.getPointPlayerOne()));
                } else {
                    match.setPointPlayerTwo(increasePointPlayerInTie(match.getPointPlayerTwo()));
                }
            }
            case FININSHED -> {
                return;
            }
            default -> throw new RuntimeException("Something went wrong in addPointToWinnerOfGame() or you in finished state try to add point");
        }
        updateGameScore(match);
        setOrUpdateGameStatus(match);
        MatchesInProgress.updateMatch(matchId, match);
    }

    private int increasePointPlayerInGame(int playerPoint) {
        if (playerPoint < 30) {
            return playerPoint + 15;
        } else {
            return playerPoint + 10;
        }
    }

    private int increasePointPlayerInAdvantage(int playerPoint) {
        return playerPoint + 5;
    }

    private int increasePointPlayerInTie(int playerPoint) {
        return ++playerPoint;
    }

    private void setOrUpdateGameStatus(Match match) {
        if (match.getPointPlayerOne() == 40 && match.getPointPlayerTwo() == 40) {
            match.setState(State.ADVANTAGE);
        } else if (match.getGamePlayerOne() == 6 && match.getGamePlayerTwo() == 6) {
            match.setState(State.TIE);
        } else if (match.getSetPlayerOne() == 2 || match.getSetPlayerTwo() == 2) {
            match.setState(State.FININSHED);
            resetPlayersPointsAndGames(match);
        }
    }

    private void updateGameScore(Match match) {
        switch (match.getState()) {
            case GAME -> {
                if (match.getPointPlayerOne() == 50) {
                    resetPlayersPoints(match);
                    match.setGamePlayerOne(match.getGamePlayerOne() + 1);
                } else if (match.getPointPlayerTwo() == 50) {
                    resetPlayersPoints(match);
                    //todo write a function to increase a game player one or two
                    match.setGamePlayerTwo(match.getGamePlayerTwo() + 1);
                }
                if (match.getGamePlayerOne() == 6 && match.getGamePlayerTwo() < 5) {
                    resetPlayersGames(match);
                    match.setSetPlayerOne(match.getSetPlayerOne() + 1);
                } else if (match.getGamePlayerOne() == 7 && match.getGamePlayerTwo() == 5) {
                    resetPlayersGames(match);
                    match.setSetPlayerOne(match.getSetPlayerOne() + 1);
                } else if (match.getGamePlayerTwo() == 6 && match.getGamePlayerOne() < 5) {
                    resetPlayersGames(match);
                    match.setSetPlayerTwo(match.getSetPlayerTwo() + 1);
                } else if (match.getGamePlayerTwo() == 7 && match.getGamePlayerOne() == 5) {
                    resetPlayersGames(match);
                    match.setSetPlayerTwo(match.getSetPlayerTwo() + 1);
                }
            }
            case ADVANTAGE -> {
                if (match.getPointPlayerOne() == 45 && match.getPointPlayerTwo() == 45) {
                    match.setPointPlayerOne(40);
                    match.setPointPlayerTwo(40);
                } else if (match.getPointPlayerOne() == 50) {
                    resetPlayersPoints(match);
                    match.setGamePlayerOne(match.getGamePlayerOne() + 1);
                    match.setState(State.GAME);
                } else if (match.getPointPlayerTwo() == 50) {
                    resetPlayersPoints(match);
                    match.setGamePlayerOne(match.getGamePlayerTwo() + 1);
                    match.setState(State.GAME);
                }
            }
            case TIE -> {
                if (match.getPointPlayerOne() == 7) {
                    resetPlayersPointsAndGames(match);
                    match.setSetPlayerOne(match.getSetPlayerOne() + 1);
                    match.setState(State.GAME);
                } else if (match.getPointPlayerTwo() == 7) {
                    resetPlayersPointsAndGames(match);
                    match.setSetPlayerTwo(match.getSetPlayerTwo() + 1);
                    match.setState(State.GAME);
                }
            }
        }
    }

    private void resetPlayersPoints(Match match) {
        match.setPointPlayerOne(0);
        match.setPointPlayerTwo(0);
    }

    private void resetPlayersGames(Match match) {
        match.setGamePlayerOne(0);
        match.setGamePlayerTwo(0);
    }

    private void resetPlayersPointsAndGames(Match match) {
        match.setPointPlayerOne(0);
        match.setPointPlayerTwo(0);
        match.setGamePlayerOne(0);
        match.setGamePlayerTwo(0);
    }
}