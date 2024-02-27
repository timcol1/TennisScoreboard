package avlyakulov.timur.service;

import avlyakulov.timur.model.MatchScoreModel;
import avlyakulov.timur.util.HibernateUtilH2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class MatchesService {

    private SessionFactory sessionFactory;

    private final int matchesPerPage = 5;

    public MatchesService() {
        this.sessionFactory = HibernateUtilH2.getSessionFactory();
    }

    public List<MatchScoreModel> getMatchesByOffsetAndLimit() {
        try (Session session = sessionFactory.openSession()) {
            Query<MatchScoreModel> hqlFindAllMatches = session.createNamedQuery("HQL_FindAllMatches", MatchScoreModel.class);
            hqlFindAllMatches.setFirstResult(0);
            hqlFindAllMatches.setMaxResults(matchesPerPage);
            return hqlFindAllMatches.list();
        }
    }

    public List<MatchScoreModel> getMatchesByOffsetAndLimit(int offset) {
        try (Session session = sessionFactory.openSession()) {
            Query<MatchScoreModel> hqlFindAllMatches = session.createNamedQuery("HQL_FindAllMatches", MatchScoreModel.class);
            hqlFindAllMatches.setFirstResult((offset - 1) * 5);
            hqlFindAllMatches.setMaxResults(matchesPerPage);
            return hqlFindAllMatches.list();
        }
    }
}