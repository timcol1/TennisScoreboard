package avlyakulov.timur.servlet;

import avlyakulov.timur.dto.MatchInProgressResponse;
import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchesInProgress;
import avlyakulov.timur.model.Player;
import avlyakulov.timur.model.State;
import avlyakulov.timur.service.FinishedMatchesPersistenceService;
import avlyakulov.timur.service.MatchInProgressService;
import avlyakulov.timur.service.MatchScoreCalculationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet(urlPatterns = "/match-score")
public class MatchScoreController extends HttpServlet {
    private MatchInProgressService matchInProgressService;

    private MatchScoreCalculationService matchScoreCalculationService;

    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @Override
    public void init() throws ServletException {
        matchInProgressService = new MatchInProgressService();
        matchScoreCalculationService = new MatchScoreCalculationService();
        finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        MatchInProgressResponse matchInProgressResponse = matchInProgressService.getMatchById(matchId);
        req.setAttribute("match", matchInProgressResponse);
        req.getRequestDispatcher("/match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        int winnerId = Integer.parseInt(req.getParameter("winnerId"));
        matchScoreCalculationService.addPointToWinnerOfGame(winnerId, matchId);
        Match match = MatchesInProgress.getMatchById(matchId);
        //todo make this logic in service class finished persistence service
        if (match.getState().equals(State.FININSHED)) {
            Player winner = getPlayerWinner(match);
            finishedMatchesPersistenceService.saveMatch(match, winner);
            String winnerName = winner.getName();
            req.setAttribute("winnerName", winnerName);
            MatchInProgressResponse matchInProgressResponse = matchInProgressService.getMatchById(matchId);
            req.setAttribute("match", matchInProgressResponse);
            req.getRequestDispatcher("/match-finished.jsp").forward(req, resp);
        } else {
            MatchInProgressResponse matchInProgressResponse = matchInProgressService.getMatchById(matchId);
            req.setAttribute("match", matchInProgressResponse);
            req.getRequestDispatcher("/match.jsp").forward(req, resp);
        }
    }

    private Player getPlayerWinner(Match match) {
        if (match.getSetPlayerOne() == 2) {
            return match.getPlayerOne();
        } else {
            return match.getPlayerTwo();
        }
    }
}