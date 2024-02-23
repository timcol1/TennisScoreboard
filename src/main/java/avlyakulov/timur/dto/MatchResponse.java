package avlyakulov.timur.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchResponse {

    private String playerOneName;

    private String playerTwoName;

    private Integer playerOneId;

    private Integer playerTwoId;

    private int pointPlayerOne;

    private int pointPlayerTwo;

    private int gamePlayerOne;

    private int gamePlayerTwo;

    private int setPlayerOne;

    private int setPlayerTwo;

    public MatchResponse(String playerOneName, String playerTwoName, Integer playerOneId, Integer playerTwoId, int pointPlayerOne, int pointPlayerTwo, int gamePlayerOne, int gamePlayerTwo, int setPlayerOne, int setPlayerTwo) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.pointPlayerOne = pointPlayerOne;
        this.pointPlayerTwo = pointPlayerTwo;
        this.gamePlayerOne = gamePlayerOne;
        this.gamePlayerTwo = gamePlayerTwo;
        this.setPlayerOne = setPlayerOne;
        this.setPlayerTwo = setPlayerTwo;
    }
}