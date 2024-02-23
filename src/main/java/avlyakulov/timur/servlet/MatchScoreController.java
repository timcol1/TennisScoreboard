package avlyakulov.timur.servlet;


import avlyakulov.timur.dto.MatchResponse;
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
        MatchResponse match = matchInProgressService.getMatchById(matchId);
        req.setAttribute("match", match);
        req.getRequestDispatcher("/match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        int winnerId = Integer.parseInt(req.getParameter("winnerId"));
        matchScoreCalculationService.addPointToWinnerOfGame(winnerId, matchId);
        MatchResponse match = matchInProgressService.getMatchById(matchId);
        req.setAttribute("match", match);
        req.getRequestDispatcher("/match.jsp").forward(req, resp);
    }
}
