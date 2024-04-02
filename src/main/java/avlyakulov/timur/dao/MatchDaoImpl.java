package avlyakulov.timur.dao;

import avlyakulov.timur.model.MatchScoreModel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class MatchDaoImpl extends HibernateDao implements MatchDao {

    private final int matchesPerPage = 5;

    @Override
    public void save(MatchScoreModel matchScoreModel) {
        executeInTransaction(session -> session.persist(matchScoreModel));
    }


    @Override
    public List<MatchScoreModel> getMatches(Integer page, String playerName) {
        return executeNotInTransaction(session -> {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<MatchScoreModel> criteriaQuery = builder.createQuery(MatchScoreModel.class);
            Root<MatchScoreModel> root = criteriaQuery.from(MatchScoreModel.class);
            root.fetch("playerOne", JoinType.INNER);
            root.fetch("playerTwo", JoinType.INNER);
            root.fetch("winner", JoinType.INNER);
            criteriaQuery.select(root);
            if (playerName != null) {
                criteriaQuery.where(builder.or(
                                builder.equal(root.get("playerOne").get("name"), playerName),
                                builder.equal(root.get("playerTwo").get("name"), playerName)
                        )
                );
            }
            return session.createQuery(criteriaQuery)
                    .setMaxResults(matchesPerPage)
                    .setFirstResult((page - 1) * 5)
                    .getResultList();
        });
    }

    @Override
    public long getNumberMatches() {
        return executeNotInTransaction(session -> {
            return session.createQuery("select count(*) from MatchScoreModel", Long.class)
                    .getSingleResult();
        });
    }

    @Override
    public int getNumberMatchesByName(String playerName) {
        return executeNotInTransaction(session -> {
            return session.createNamedQuery("HQL_FindAllMatchesByPlayerName", MatchScoreModel.class)
                    .setParameter("name", playerName)
                    .getResultList().size();
        });
    }
}