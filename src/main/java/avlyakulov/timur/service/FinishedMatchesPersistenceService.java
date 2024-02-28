package avlyakulov.timur.service;

import avlyakulov.timur.model.*;
import avlyakulov.timur.util.HibernateUtilH2;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.UUID;

@Slf4j
public class FinishedMatchesPersistenceService {
    private final SessionFactory sessionFactory;

    public FinishedMatchesPersistenceService() {
        this.sessionFactory = HibernateUtilH2.getSessionFactory();
    }

    //todo delete match from in memory collection Map
    public void saveMatch(Match match, Player winner) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();//открываем транзакцию
            MatchScoreModel matchFinished = new MatchScoreModel(match.getPlayerOne(), match.getPlayerTwo(), winner);
            session.persist(matchFinished);
            session.getTransaction().commit();//закрываем транзакцию
        }
    }

    public boolean checkMatchFinished(Match match) {
        return match.getState().equals(State.FININSHED);
    }

    private void deleteMatchFromMemory(UUID matchId) {
        MatchesInProgress.deleteMatchById(matchId);
    }
}