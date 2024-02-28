package avlyakulov.timur.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MatchFinishedResponse {

    private String playerOneName;

    private String playerTwoName;

    private String winnerName;

    private int playerOneSet;

    private int playerTwoSet;
}
