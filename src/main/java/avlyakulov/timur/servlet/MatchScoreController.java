package avlyakulov.timur.servlet;

import avlyakulov.timur.dto.MatchFinishedResponse;
import avlyakulov.timur.dto.MatchInProgressResponse;
import avlyakulov.timur.model.Match;
import avlyakulov.timur.model.MatchesInProgress;
import avlyakulov.timur.model.Player;
import avlyakulov.timur.service.FinishedMatchesPersistenceService;
import avlyakulov.timur.service.MatchInProgressService;
import avlyakulov.timur.service.MatchScoreCalculationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
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
        if (finishedMatchesPersistenceService.checkMatchFinished(matchId)) {
            req.getRequestDispatcher("/match-finished-memory.jsp").forward(req, resp);
            log.info("User tries to get finished match from memory by matchId in url");
        } else {
            req.setAttribute("match", matchInProgressResponse);
            req.getRequestDispatcher("/match.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID matchId = UUID.fromString(req.getParameter("uuid"));
        int winnerId = Integer.parseInt(req.getParameter("winnerId"));
        if (finishedMatchesPersistenceService.checkMatchFinished(matchId)) {
            req.getRequestDispatcher("/match-finished-memory.jsp").forward(req, resp);
            log.info("User tries to get finished match from memory by matchId in url");
        } else {
            matchScoreCalculationService.addPointToWinnerOfGame(winnerId, matchId);
            Match match = MatchesInProgress.getMatchById(matchId);
            if (finishedMatchesPersistenceService.checkMatchFinished(match)) {
                Player winner = getPlayerWinner(match);
                MatchFinishedResponse matchFinished = matchInProgressService.getFinishedMatchById(match, winner);
                req.setAttribute("match", matchFinished);
                finishedMatchesPersistenceService.saveMatch(matchId, winner);
                req.getRequestDispatcher("/match-finished.jsp").forward(req, resp);
                log.info("Match with such an id {} was finished", matchId);
            } else {
                MatchInProgressResponse matchInProgressResponse = matchInProgressService.getMatchById(match);
                req.setAttribute("match", matchInProgressResponse);
                req.getRequestDispatcher("/match.jsp").forward(req, resp);
            }
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