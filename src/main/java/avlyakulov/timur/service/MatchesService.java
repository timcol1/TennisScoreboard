package avlyakulov.timur.service;

import avlyakulov.timur.dto.MatchScoreModelResponse;
import avlyakulov.timur.mapper.MatchMapper;
import avlyakulov.timur.model.MatchScoreModel;
import avlyakulov.timur.util.CapitalizeFirstLetter;
import avlyakulov.timur.util.HibernateSingletonUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

public class MatchesService {

    private SessionFactory sessionFactory;

    private final int matchesPerPage = 5;
    private final double matchesPerPageDouble = 5.0;

    public MatchesService() {
        this.sessionFactory = HibernateSingletonUtil.getSessionFactory();
    }


    public List<MatchScoreModelResponse> getMatches(Integer page, String playerName) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MatchScoreModel> criteriaQuery = builder.createQuery(MatchScoreModel.class);
            Root<MatchScoreModel> root = criteriaQuery.from(MatchScoreModel.class);
            root.fetch("playerOne", JoinType.INNER);
            root.fetch("playerTwo", JoinType.INNER);
            root.fetch("winner", JoinType.INNER);
            criteriaQuery.select(root);
            if (playerName != null) {
                playerName = CapitalizeFirstLetter.capitalizeFirstLetter(playerName);
                criteriaQuery.where(builder.or(
                                builder.equal(root.get("playerOne").get("name"), playerName),
                                builder.equal(root.get("playerTwo").get("name"), playerName)
                        )
                );
            }
            List<MatchScoreModel> matches = session.createQuery(criteriaQuery)
                    .setMaxResults(matchesPerPage)
                    .setFirstResult((page - 1) * 5)
                    .getResultList();
            return MatchMapper.INSTANCE.mapToListMatchScoreModelResponse(matches);
        }
    }

    public int getPagesOfMatches(List<MatchScoreModelResponse> matches) {
        double number = matches.size() / matchesPerPageDouble;
        return (int) Math.ceil(number);
    }
}