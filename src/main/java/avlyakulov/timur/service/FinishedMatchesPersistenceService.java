package avlyakulov.timur.service;

import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchScoreModel;
import avlyakulov.timur.model.MatchesInProgress;
import avlyakulov.timur.model.Player;
import avlyakulov.timur.util.HibernateUtilH2;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class FinishedMatchesPersistenceService {
    private final SessionFactory sessionFactory;

    public FinishedMatchesPersistenceService() {
        this.sessionFactory = HibernateUtilH2.getSessionFactory();
    }

    public void saveMatch(Match match, Player winner) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();//открываем транзакцию
            MatchScoreModel matchFinished = new MatchScoreModel(match.getPlayerOne(), match.getPlayerTwo(), winner);
            session.persist(matchFinished);
            session.getTransaction().commit();//закрываем транзакцию
        }
    }
}