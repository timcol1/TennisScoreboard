package avlyakulov.timur.service;

import avlyakulov.timur.dao.MatchDao;
import avlyakulov.timur.dao.MatchDaoImpl;
import avlyakulov.timur.dto.MatchScoreModelResponse;
import avlyakulov.timur.mapper.MatchMapper;
import avlyakulov.timur.model.MatchScoreModel;
import avlyakulov.timur.util.CapitalizeFirstLetter;

import java.util.List;
import java.util.Optional;

public class MatchesService {

    private final MatchDao matchDao = new MatchDaoImpl();

    private final double matchesPerPageDouble = 5.0;


    public List<MatchScoreModelResponse> getMatches(Integer page, String playerName) {
        if (playerName != null) {
            playerName = CapitalizeFirstLetter.capitalizeFirstLetter(playerName);
        }
        List<MatchScoreModel> matches = matchDao.getMatches(page, playerName);
        return MatchMapper.INSTANCE.mapToListMatchScoreModelResponse(matches);

    }

    public int getPagesOfMatches(String playerName) {
        if (playerName != null) {
            playerName = CapitalizeFirstLetter.capitalizeFirstLetter(playerName);
            long numberMatches = matchDao.getNumberMatchesByName(playerName);
            double number = numberMatches / matchesPerPageDouble;
            return (int) Math.ceil(number);
        } else {
            long numberMatches = matchDao.getNumberMatches();
            double number = numberMatches / matchesPerPageDouble;
            return (int) Math.ceil(number);
        }
    }

}