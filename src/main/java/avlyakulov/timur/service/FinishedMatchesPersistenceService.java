package avlyakulov.timur.service;

import avlyakulov.timur.dao.MatchDao;
import avlyakulov.timur.dao.MatchDaoImpl;
import avlyakulov.timur.dao.MatchesInProgress;
import avlyakulov.timur.model.*;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class FinishedMatchesPersistenceService {
    private final MatchDao matchDao = new MatchDaoImpl();

    public void saveMatch(UUID matchId, Player winner) {
        Match match = MatchesInProgress.getMatchById(matchId);
        MatchScoreModel matchFinished = new MatchScoreModel(match.getPlayerOne(), match.getPlayerTwo(), winner);
        matchDao.save(matchFinished);
        MatchPagesService.updateNumberMatchesPages();
        deleteMatchFromMemory(matchId);
        log.info("Match with such id {} was deleted from memory", matchId);
    }

    public boolean checkMatchFinished(Match match) {
        if (match == null || match.getState().equals(State.FINISHED)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMatchFinished(UUID matchId) {
        Match match = MatchesInProgress.getMatchById(matchId);
        if (match == null || match.getState().equals(State.FINISHED)) {
            return true;
        } else {
            return false;
        }
    }

    private void deleteMatchFromMemory(UUID matchId) {
        MatchesInProgress.deleteMatchById(matchId);
    }
}