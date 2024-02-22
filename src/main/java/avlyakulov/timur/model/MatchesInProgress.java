package avlyakulov.timur.model;

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
}