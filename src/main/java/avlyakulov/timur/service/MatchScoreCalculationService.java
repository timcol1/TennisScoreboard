package avlyakulov.timur.service;

import avlyakulov.timur.model.Match;
import avlyakulov.timur.dao.MatchesInProgress;
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
            case FINISHED -> {
                return;
            }
            default ->
                    throw new RuntimeException("Something went wrong in addPointToWinnerOfGame() or you in finished state try to add point");
        }
        updateGameScore(match);
        updateGameStatus(match);
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

    private void updateGameStatus(Match match) {
        if (match.getPointPlayerOne() == 40 && match.getPointPlayerTwo() == 40) {
            match.setState(State.ADVANTAGE);
        } else if (match.getGamePlayerOne() == 6 && match.getGamePlayerTwo() == 6) {
            match.setState(State.TIE);
        } else if (match.getSetPlayerOne() == 2 || match.getSetPlayerTwo() == 2) {
            match.setState(State.FINISHED);
            resetPlayersPointsAndGames(match);
        }
    }

    private void updateGameScore(Match match) {
        switch (match.getState()) {
            case GAME -> {
                if (match.getPointPlayerOne() == 50) {
                    resetPlayersPoints(match);
                    increasePlayerGame(PlayerNumber.PLAYER_ONE, match);
                } else if (match.getPointPlayerTwo() == 50) {
                    resetPlayersPoints(match);
                    increasePlayerGame(PlayerNumber.PLAYER_TWO, match);
                }
                if (match.getGamePlayerOne() == 6 && match.getGamePlayerTwo() < 5) {
                    resetPlayersGames(match);
                    increasePlayerSet(PlayerNumber.PLAYER_ONE, match);
                } else if (match.getGamePlayerOne() == 7 && match.getGamePlayerTwo() == 5) {
                    resetPlayersGames(match);
                    increasePlayerSet(PlayerNumber.PLAYER_ONE, match);
                } else if (match.getGamePlayerTwo() == 6 && match.getGamePlayerOne() < 5) {
                    resetPlayersGames(match);
                    increasePlayerSet(PlayerNumber.PLAYER_TWO, match);
                } else if (match.getGamePlayerTwo() == 7 && match.getGamePlayerOne() == 5) {
                    resetPlayersGames(match);
                    increasePlayerSet(PlayerNumber.PLAYER_TWO, match);
                }
            }
            case ADVANTAGE -> {
                if (match.getPointPlayerOne() == 45 && match.getPointPlayerTwo() == 45) {
                    match.setPointPlayerOne(40);
                    match.setPointPlayerTwo(40);
                } else if (match.getPointPlayerOne() == 50) {
                    resetPlayersPoints(match);
                    increasePlayerGame(PlayerNumber.PLAYER_ONE, match);
                    match.setState(State.GAME);
                } else if (match.getPointPlayerTwo() == 50) {
                    resetPlayersPoints(match);
                    increasePlayerGame(PlayerNumber.PLAYER_TWO, match);
                    match.setState(State.GAME);
                }
            }
            case TIE -> {
                if (match.getPointPlayerOne() == 7 && match.getPointPlayerTwo() <= 5) {
                    resetPlayersPointsAndGames(match);
                    increasePlayerSet(PlayerNumber.PLAYER_ONE, match);
                    match.setState(State.GAME);
                } else if (match.getPointPlayerTwo() == 7 && match.getPointPlayerOne() <= 5) {
                    resetPlayersPointsAndGames(match);
                    increasePlayerSet(PlayerNumber.PLAYER_TWO, match);
                    match.setState(State.GAME);
                } else if (match.getPointPlayerOne() >= 7 && (match.getPointPlayerOne() - match.getPointPlayerTwo() == 2)) {
                    resetPlayersPointsAndGames(match);
                    increasePlayerSet(PlayerNumber.PLAYER_ONE, match);
                    match.setState(State.GAME);
                } else if (match.getPointPlayerTwo() >= 7 && (match.getPointPlayerTwo() - match.getPointPlayerOne() == 2)) {
                    resetPlayersPointsAndGames(match);
                    increasePlayerSet(PlayerNumber.PLAYER_TWO, match);
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

    private void increasePlayerGame(PlayerNumber playerNumber, Match match) {
        switch (playerNumber) {
            case PLAYER_ONE -> match.setGamePlayerOne(match.getGamePlayerOne() + 1);

            case PLAYER_TWO -> match.setGamePlayerTwo(match.getGamePlayerTwo() + 1);
        }
    }

    private void increasePlayerSet(PlayerNumber playerNumber, Match match) {
        switch (playerNumber) {
            case PLAYER_ONE -> match.setSetPlayerOne(match.getSetPlayerOne() + 1);

            case PLAYER_TWO -> match.setSetPlayerTwo(match.getSetPlayerTwo() + 1);
        }
    }
}

enum PlayerNumber {
    PLAYER_ONE,
    PLAYER_TWO
}