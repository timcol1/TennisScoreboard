package avlyakulov.timur.service;

import avlyakulov.timur.dto.MatchResponse;
import avlyakulov.timur.mapper.MatchMapper;
import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchesInProgress;

import java.util.UUID;

public class MatchInProgressService {


    public MatchResponse getMatchById(UUID uuid) {
        Match match = MatchesInProgress.getMatchById(uuid);

        return MatchMapper.INSTANCE.mapToMatchResponse(match);
    }
}