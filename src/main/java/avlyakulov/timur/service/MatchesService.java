package avlyakulov.timur.service;

import avlyakulov.timur.dto.MatchScoreModelResponse;
import avlyakulov.timur.mapper.MatchMapper;
import avlyakulov.timur.model.MatchScoreModel;
import avlyakulov.timur.util.CapitalizeFirstLetter;
import avlyakulov.timur.util.HibernateSingletonUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class MatchesService {

    private SessionFactory sessionFactory;

    private final int matchesPerPage = 5;

    public MatchesService() {
        this.sessionFactory = HibernateSingletonUtil.getSessionFactory();
    }

    public List<MatchScoreModelResponse> getMatchesByOffsetAndLimit() {
        try (Session session = sessionFactory.openSession()) {
            Query<MatchScoreModel> hqlFindAllMatches = session.createNamedQuery("HQL_FindAllMatches", MatchScoreModel.class)
                    .setFirstResult(0)
                    .setMaxResults(matchesPerPage);
            return MatchMapper.INSTANCE.mapToListMatchScoreModelResponse(hqlFindAllMatches.list());
        }
    }

    public List<MatchScoreModelResponse> getMatchesByOffsetAndLimit(int offset) {
        try (Session session = sessionFactory.openSession()) {
            Query<MatchScoreModel> hqlFindAllMatches = session.createNamedQuery("HQL_FindAllMatches", MatchScoreModel.class)
                    .setFirstResult((offset - 1) * 5)
                    .setMaxResults(matchesPerPage);

            return MatchMapper.INSTANCE.mapToListMatchScoreModelResponse(hqlFindAllMatches.list());
        }
    }

    public List<MatchScoreModelResponse> getMatchesByOffsetAndLimitAndName(int offset, String playerName) {
        playerName = CapitalizeFirstLetter.capitalizeFirstLetter(playerName);
        try (Session session = sessionFactory.openSession()) {
            Query<MatchScoreModel> hqlFindAllMatches = session.createNamedQuery("HQL_FindAllMatchesByPlayerName", MatchScoreModel.class)
                    .setParameter("name", playerName)
                    .setFirstResult((offset - 1) * 5)
                    .setMaxResults(matchesPerPage);

            return MatchMapper.INSTANCE.mapToListMatchScoreModelResponse(hqlFindAllMatches.list());
        }
    }
}