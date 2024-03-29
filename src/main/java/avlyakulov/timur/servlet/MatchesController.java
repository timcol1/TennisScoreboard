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
        Integer page = validateAndParsePage(pageStr, req);
        List<MatchScoreModelResponse> matches = matchesService.getMatches(page, playerName);
        req.setAttribute("matches", matches);
        req.getRequestDispatcher("/pages/matches.jsp").forward(req, resp);
    }

    private Integer validateAndParsePage(String pageStr, HttpServletRequest req) {
        if (pageStr != null && !pageStr.isBlank() && pageStr.matches("[0-9]?[0-9]")) {
            int page = Integer.parseInt(pageStr);
            Integer pageValidated = validatePageNumber(page);
            req.setAttribute("page", pageValidated);
            return pageValidated;
        } else {
            req.setAttribute("page", 1);
            return 1;
        }
    }

    private Integer validatePageNumber(int pageNumber) {
        return pageNumber < 1 ? 1 : pageNumber;
    }
}