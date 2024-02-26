package avlyakulov.timur.service;

import avlyakulov.timur.dto.MatchResponse;
import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchesInProgress;

import java.util.UUID;

public class MatchInProgressService {

    public MatchResponse getMatchById(UUID uuid) {
        Match match = MatchesInProgress.getMatchById(uuid);
        return new MatchResponse(
                match.getPlayerOne(),
                match.getPlayerTwo(),
                match.getPointPlayerOne(),
                match.getPointPlayerTwo(),
                match.getGamePlayerOne(),
                match.getGamePlayerTwo(),
                match.getSetPlayerOne(),
                match.getSetPlayerTwo()
        );
    }
}