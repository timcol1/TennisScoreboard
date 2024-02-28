package avlyakulov.timur.service;

import avlyakulov.timur.dto.MatchFinishedResponse;
import avlyakulov.timur.dto.MatchInProgressResponse;
import avlyakulov.timur.mapper.MatchMapper;
import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchesInProgress;
import avlyakulov.timur.model.Player;

import java.util.UUID;

public class MatchInProgressService {


    public MatchInProgressResponse getMatchById(UUID matchId) {
        Match match = MatchesInProgress.getMatchById(matchId);
        return MatchMapper.INSTANCE.mapToMatchResponse(match);
    }

    public MatchInProgressResponse getMatchById(Match match) {
        return MatchMapper.INSTANCE.mapToMatchResponse(match);
    }

    public MatchFinishedResponse getFinishedMatchById(Match match, Player winner) {
        return MatchMapper.INSTANCE.mapToMatchFinishedResponse(match, winner);
    }
}