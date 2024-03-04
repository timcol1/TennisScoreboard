package avlyakulov.timur.service;

import avlyakulov.timur.dao.PlayerDao;
import avlyakulov.timur.dao.PlayerDaoImpl;
import avlyakulov.timur.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;

@Slf4j
public class PlayerService {
    private final PlayerDao playerDao = new PlayerDaoImpl();

    public int createPlayerOrGetPlayerIfExists(Player player) {
        try {
            player = playerDao.create(player);
        } catch (ConstraintViolationException e) {
            log.info("Player with name {} exists in db", player.getName());
            return playerDao.findByName(player.getName()).getId();
        }
        return player.getId();
    }
}