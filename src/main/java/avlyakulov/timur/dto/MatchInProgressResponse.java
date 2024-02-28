package avlyakulov.timur.dto;

import avlyakulov.timur.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchInProgressResponse {
    //todo create mapstruct mapper
    private Player playerOne;

    private Player playerTwo;

    private int pointPlayerOne;

    private int pointPlayerTwo;

    private int gamePlayerOne;

    private int gamePlayerTwo;

    private int setPlayerOne;

    private int setPlayerTwo;

    public MatchInProgressResponse(Player playerOne, Player playerTwo, int pointPlayerOne, int pointPlayerTwo, int gamePlayerOne, int gamePlayerTwo, int setPlayerOne, int setPlayerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.pointPlayerOne = pointPlayerOne;
        this.pointPlayerTwo = pointPlayerTwo;
        this.gamePlayerOne = gamePlayerOne;
        this.gamePlayerTwo = gamePlayerTwo;
        this.setPlayerOne = setPlayerOne;
        this.setPlayerTwo = setPlayerTwo;
    }
}