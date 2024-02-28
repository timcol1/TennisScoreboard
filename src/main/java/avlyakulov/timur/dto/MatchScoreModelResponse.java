package avlyakulov.timur.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchScoreModelResponse {

    private int id;

    private String playerOneName;

    private String playerTwoName;

    private String winnerName;
}