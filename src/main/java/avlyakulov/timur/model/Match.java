package avlyakulov.timur.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Match {

    private UUID matchId;

    private int player1Id;

    private int player2Id;

    private int player1Score;

    private int player2Score;

    private int player1Set;

    private int player2Set;

}