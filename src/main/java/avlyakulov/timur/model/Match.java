package avlyakulov.timur.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {

    private Integer playerOneId;

    private Integer playerTwoId;

    private int gamePlayerOne;

    private int gamePlayerTwo;

    private int setPlayerOne;

    private int setPlayerTwo;

    public Match(int playerOneId, int playerTwoId) {
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
    }
}