package avlyakulov.timur.service;

import avlyakulov.timur.model.Match;
import avlyakulov.timur.dao.MatchesInProgress;
import avlyakulov.timur.model.Player;
import avlyakulov.timur.util.CapitalizeFirstLetter;

import java.util.UUID;

public class CreateMatchService {
    private PlayerService playerService;

    public CreateMatchService() {
        this.playerService = new PlayerService();
    }

    public UUID createMatch(String playerOneName, String playerTwoName) {
        playerOneName = CapitalizeFirstLetter.capitalizeFirstLetter(playerOneName);
        playerTwoName = CapitalizeFirstLetter.capitalizeFirstLetter(playerTwoName);
        Match match = addPlayersToMatch(playerOneName, playerTwoName);
        UUID matchId = UUID.randomUUID();
        MatchesInProgress.createMatch(matchId, match);
        return matchId;
    }

    private Match addPlayersToMatch(String playerOneName, String playerTwoName) {
        Player playerOne = new Player(playerOneName);
        Player playerTwo = new Player(playerTwoName);
        playerOne.setId(playerService.createPlayerOrGetPlayerIfExists(playerOne));
        playerTwo.setId(playerService.createPlayerOrGetPlayerIfExists(playerTwo));

        return new Match(playerOne, playerTwo);
    }


}