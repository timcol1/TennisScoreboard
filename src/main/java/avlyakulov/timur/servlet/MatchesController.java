package avlyakulov.timur.servlet;

import avlyakulov.timur.dto.MatchScoreModelResponse;
import avlyakulov.timur.service.MatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/matches")
public class MatchesController extends HttpServlet {
    private MatchesService matchesService;

    @Override
    public void init() throws ServletException {
        matchesService = new MatchesService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        String playerName = req.getParameter("filter_by_player_name");
        List<MatchScoreModelResponse> matches;
        if (isPageStringNotValid(pageStr) && playerName == null) {
            matches = matchesService.getMatchesByOffsetAndLimit();
            req.setAttribute("page", 1);
        } else if (!isPageStringNotValid(pageStr) && playerName == null) {
            int page = validatePageNumber(Integer.parseInt(pageStr));
            matches = matchesService.getMatchesByOffsetAndLimit(page);
            req.setAttribute("page", page);
        } else if (isPageStringNotValid(pageStr) && playerName != null) {
            matches = matchesService.getMatchesByOffsetAndLimitAndName(1, playerName);
            req.setAttribute("page", 1);
        } else {
            int page = validatePageNumber(Integer.parseInt(pageStr));
            matches = matchesService.getMatchesByOffsetAndLimitAndName(page, playerName);
            req.setAttribute("page", page);
        }
        req.setAttribute("matches", matches);
        req.getRequestDispatcher("/pages/matches.jsp").forward(req, resp);
    }

    private boolean isPageStringNotValid(String pageStr) {
        if (pageStr == null || pageStr.isBlank() || !pageStr.matches("[0-9]?[0-9]")) {
            return true;
        } else {
            return false;
        }
    }

    private int validatePageNumber(int pageNumber) {
        return pageNumber < 1 ? 1 : pageNumber;
    }
}