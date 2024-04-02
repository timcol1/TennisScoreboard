package avlyakulov.timur.dao;

import avlyakulov.timur.model.Player;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PlayerDaoImpl extends HibernateDao implements PlayerDao {

    @Override
    public Player create(Player player) {
        executeInTransaction(session -> session.persist(player));
        log.info("Create player with name {}", player.getName());
        return player;
    }

    @Override
    public Player findByName(String playerName) {
        return executeNotInTransaction(session -> {
            return session.createNamedQuery("HQL_FindPlayerByName", Player.class)
                    .setParameter("playerName", playerName)
                    .getSingleResult();
        });
    }
}