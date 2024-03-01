package avlyakulov.timur.service;

import avlyakulov.timur.model.*;
import avlyakulov.timur.util.HibernateSingletonUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.UUID;

@Slf4j
public class FinishedMatchesPersistenceService {
    private final SessionFactory sessionFactory;

    public FinishedMatchesPersistenceService() {
        this.sessionFactory = HibernateSingletonUtil.getSessionFactory();
    }


    public void saveMatch(UUID matchId, Player winner) {
        Match match = MatchesInProgress.getMatchById(matchId);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();//открываем транзакцию
            MatchScoreModel matchFinished = new MatchScoreModel(match.getPlayerOne(), match.getPlayerTwo(), winner);
            session.persist(matchFinished);
            session.getTransaction().commit();//закрываем транзакцию
        }
        deleteMatchFromMemory(matchId);
        log.info("Match with such id {} was deleted from memory", matchId);
    }

    public boolean checkMatchFinished(Match match) {
        if (match == null) {
            return true;
        } else if (match.getState().equals(State.FINISHED)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMatchFinished(UUID matchId) {
        Match match = MatchesInProgress.getMatchById(matchId);
        if (match == null) {
            return true;
        } else if (match.getState().equals(State.FINISHED)) {
            return true;
        } else {
            return false;
        }
    }

    private void deleteMatchFromMemory(UUID matchId) {
        MatchesInProgress.deleteMatchById(matchId);
    }
}