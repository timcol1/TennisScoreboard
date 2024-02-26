package avlyakulov.timur.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {

    private Integer playerOneId;

    private Integer playerTwoId;

    private String playerOneName;

    private String playerTwoName;

    private int pointPlayerOne;

    private int pointPlayerTwo;

    private int gamePlayerOne;

    private int gamePlayerTwo;

    private int setPlayerOne;

    private int setPlayerTwo;

    private State state;

    public Match(int playerOneId, int playerTwoId, String playerOneName, String playerTwoName) {
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.state = State.GAME;
    }
}