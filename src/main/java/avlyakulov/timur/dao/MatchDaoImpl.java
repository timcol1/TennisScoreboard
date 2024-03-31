package avlyakulov.timur.dao;

import avlyakulov.timur.model.MatchScoreModel;
import avlyakulov.timur.util.HibernateSingletonUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MatchDaoImpl implements MatchDao {

    private final SessionFactory sessionFactory = HibernateSingletonUtil.getSessionFactory();

    @Override
    public void save(MatchScoreModel matchScoreModel) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();//открываем транзакцию

            session.persist(matchScoreModel);

            session.getTransaction().commit();//закрываем транзакцию
        }
    }

    public long getNumberMatches() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select count(*) from MatchScoreModel", Long.class)
                    .getSingleResult();
        }
    }
}