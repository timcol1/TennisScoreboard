package avlyakulov.timur.service;

import avlyakulov.timur.dto.MatchInProgressResponse;
import avlyakulov.timur.mapper.MatchMapper;
import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchesInProgress;

import java.util.UUID;

public class MatchInProgressService {


    public MatchInProgressResponse getMatchById(UUID uuid) {
        Match match = MatchesInProgress.getMatchById(uuid);

        return MatchMapper.INSTANCE.mapToMatchResponse(match);
    }
}