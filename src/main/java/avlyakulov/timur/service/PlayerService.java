package avlyakulov.timur.service;

import avlyakulov.timur.model.Player;
import avlyakulov.timur.util.HibernateSingletonUtil;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@Slf4j
public class PlayerService {
    private final SessionFactory sessionFactory;

    public PlayerService() {
        sessionFactory = HibernateSingletonUtil.getSessionFactory();
    }

    public int getPlayerByNameOrCreateHimIfNotExist(Player player) {
        int playerId;
        try {
            playerId = findPlayerByName(player.getName()).getId();
            log.info("This player with such name {} exists", player.getName());
            return playerId;
        } catch (NoResultException e) {
            log.info("Player with such name {} doesn't exists in db", player.getName());
            playerId = savePlayer(player).getId();
            log.info("We are creating a player with such name {}", player.getName());
            return playerId;
        }
    }

    public Player findPlayerByName(String playerName) throws NoResultException {
        try (Session session = sessionFactory.openSession()) {
            return session.createNamedQuery("HQL_FindPlayerByName", Player.class)
                    .setParameter("playerName", playerName)
                    .getSingleResult();
        }
    }

    public Player savePlayer(Player player) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();//открываем транзакцию

            session.persist(player);

            session.getTransaction().commit();//закрываем транзакцию

            return player;
        }
    }
}