package avlyakulov.timur.dao;

import avlyakulov.timur.model.MatchScoreModel;

import java.util.List;

public interface MatchDao {

    void save(MatchScoreModel matchScoreModel);

    long getNumberMatches();

    int getNumberMatchesByName(String playerName);

    List<MatchScoreModel> getMatches(Integer page, String playerName);

}