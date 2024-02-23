package avlyakulov.timur.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {

    private Integer playerOneId;

    private Integer playerTwoId;

    private int pointPlayerOne;

    private int pointPlayerTwo;

    private int gamePlayerOne;

    private int gamePlayerTwo;

    private int setPlayerOne;

    private int setPlayerTwo;

    private State state;

    public Match(int playerOneId, int playerTwoId) {
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        state = State.GAME;
    }
}