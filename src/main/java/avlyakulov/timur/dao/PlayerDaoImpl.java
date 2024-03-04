package avlyakulov.timur.dao;

import avlyakulov.timur.model.Player;
import avlyakulov.timur.util.HibernateSingletonUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class PlayerDaoImpl implements PlayerDao {

    private final SessionFactory sessionFactory;

    public PlayerDaoImpl() {
        sessionFactory = HibernateSingletonUtil.getSessionFactory();
    }


    @Override
    public Player create(Player player) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();//открываем транзакцию

            session.persist(player);

            session.getTransaction().commit();//закрываем транзакцию

            log.info("Create player with name {}", player.getName());
            return player;
        }
    }

    @Override
    public Player findByName(String playerName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createNamedQuery("HQL_FindPlayerByName", Player.class)
                    .setParameter("playerName", playerName)
                    .getSingleResult();
        }
    }
}