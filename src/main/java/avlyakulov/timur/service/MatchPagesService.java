package avlyakulov.timur.service;

import avlyakulov.timur.dao.MatchDao;
import avlyakulov.timur.dao.MatchDaoImpl;

public class MatchPagesService {

    private static MatchDao matchDao = new MatchDaoImpl();

    private static Long numberMatches;

    private static Long numberOfMatchPages;

    private static final double matchesPerPageDouble = 5.0;

    private MatchPagesService() {
    }

    public static Long getNumberOfMatchPages() {
        if (numberOfMatchPages == null) {
            numberMatches = matchDao.getNumberMatches();
            countPagesMatches();
        }
        return numberOfMatchPages;
    }

    public static void updateNumberMatchesPages() {
        numberMatches = matchDao.getNumberMatches();
        countPagesMatches();
    }

    private static void countPagesMatches() {
        double number = numberMatches / matchesPerPageDouble;
        numberOfMatchPages = (long) Math.ceil(number);
    }
}