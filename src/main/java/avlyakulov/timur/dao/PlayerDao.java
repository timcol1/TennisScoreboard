package avlyakulov.timur.dao;

import avlyakulov.timur.model.Player;

public interface PlayerDao {

    Player create(Player player);

    Player findByName(String name);
}
