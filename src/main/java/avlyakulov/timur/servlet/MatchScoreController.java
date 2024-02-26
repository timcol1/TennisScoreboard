package avlyakulov.timur.servlet;


import avlyakulov.timur.dto.MatchResponse;
import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchesInProgress;
import avlyakulov.timur.model.State;
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

    @Override
    public void init() throws ServletException {
        matchInProgressService = new MatchInProgressService();
        matchScoreCalculationService = new MatchScoreCalculationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        MatchResponse matchResponse = matchInProgressService.getMatchById(matchId);
        req.setAttribute("match", matchResponse);
        req.getRequestDispatcher("/match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        int winnerId = Integer.parseInt(req.getParameter("winnerId"));
        matchScoreCalculationService.addPointToWinnerOfGame(winnerId, matchId);
        Match match = MatchesInProgress.getMatchById(matchId);
        if (match.getState().equals(State.FININSHED)) {
            String winnerName = getNameOfWinner(match);
            req.setAttribute("winnerName", winnerName);
            MatchResponse matchResponse = matchInProgressService.getMatchById(matchId);
            req.setAttribute("match", matchResponse);
            req.getRequestDispatcher("/match-finished.jsp").forward(req, resp);
        } else {
            MatchResponse matchResponse = matchInProgressService.getMatchById(matchId);
            req.setAttribute("match", matchResponse);
            req.getRequestDispatcher("/match.jsp").forward(req, resp);
        }
    }

    private String getNameOfWinner(Match match) {
        if (match.getSetPlayerOne() == 2) {
            return match.getPlayerOne().getName();
        } else {
            return match.getPlayerTwo().getName();
        }
    }
}