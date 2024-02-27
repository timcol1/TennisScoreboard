package avlyakulov.timur.servlet;

import avlyakulov.timur.model.MatchScoreModel;
import avlyakulov.timur.service.MatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/matches")
public class MatchesController extends HttpServlet {
    private MatchService matchService;

    @Override
    public void init() throws ServletException {
        matchService = new MatchService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageStr = req.getParameter("page");
        if (pageStr == null || pageStr.isBlank() || !pageStr.matches("[0-9]?[0-9]")) {
            List<MatchScoreModel> matchesByOffsetAndLimit = matchService.getMatchesByOffsetAndLimit();
            req.setAttribute("page", 1);
            req.setAttribute("matches", matchesByOffsetAndLimit);
            req.getRequestDispatcher("/matches.jsp").forward(req, resp);
        } else {
            int page = Integer.parseInt(req.getParameter("page"));
            List<MatchScoreModel> matchesByOffsetAndLimit = matchService.getMatchesByOffsetAndLimit(page);
            req.setAttribute("page", page);
            req.setAttribute("matches", matchesByOffsetAndLimit);
            req.getRequestDispatcher("/matches.jsp").forward(req, resp);
        }
    }
}