package avlyakulov.timur.service;

import avlyakulov.timur.dto.MatchResponse;
import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchesInProgress;
import avlyakulov.timur.util.HibernateUtilH2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.UUID;

public class MatchInProgressService {

    private final SessionFactory sessionFactory;

    public MatchInProgressService() {
        sessionFactory = HibernateUtilH2.getSessionFactory();
    }

    public MatchResponse getMatchById(UUID uuid) {
        Match match = MatchesInProgress.getMatchById(uuid);
        try (Session session = sessionFactory.openSession()) {
            String playerOneName = session.createNamedQuery("HQL_FindPlayerNameById", String.class)
                    .setParameter("id", match.getPlayerOneId()).
                    getSingleResult();
            String playerTwoName = session.createNamedQuery("HQL_FindPlayerNameById", String.class)
                    .setParameter("id", match.getPlayerTwoId()).
                    getSingleResult();
            return new MatchResponse(
                    playerOneName,
                    playerTwoName,
                    match.getPlayerOneId(),
                    match.getPlayerTwoId(),
                    match.getPointPlayerOne(),
                    match.getPointPlayerTwo(),
                    match.getGamePlayerOne(),
                    match.getGamePlayerTwo(),
                    match.getSetPlayerOne(),
                    match.getSetPlayerTwo()
                    );
        }
    }
}