package avlyakulov.timur.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchResponse {

    private String playerOneName;

    private String playerTwoName;

    private int gamePlayerOne;

    private int gamePlayerTwo;

    private int setPlayerOne;

    private int setPlayerTwo;

    public MatchResponse(String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }
}