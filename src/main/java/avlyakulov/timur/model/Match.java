package avlyakulov.timur.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {

    private Player playerOne;

    private Player playerTwo;

    private int pointPlayerOne;

    private int pointPlayerTwo;

    private int gamePlayerOne;

    private int gamePlayerTwo;

    private int setPlayerOne;

    private int setPlayerTwo;

    private State state;

    public Match(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.state = State.GAME;
    }
}