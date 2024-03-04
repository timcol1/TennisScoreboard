package avlyakulov.timur.dao;

import avlyakulov.timur.model.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MatchesInProgress {
    private static Map<UUID, Match> matchesInProgress = new HashMap<>();

    public static void createMatch(UUID matchId, Match match) {
        matchesInProgress.put(matchId, match);
    }

    public static Match getMatchById(UUID matchId) {
        return matchesInProgress.get(matchId);
    }

    public static void updateMatch(UUID matchId, Match match) {
        matchesInProgress.put(matchId, match);
    }

    public static void deleteMatchById(UUID matchId) {
        matchesInProgress.remove(matchId);
    }
}