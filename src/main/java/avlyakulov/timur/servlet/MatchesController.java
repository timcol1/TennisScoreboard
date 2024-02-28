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
        List<MatchScoreModelResponse> matches;
        if (pageStr == null || pageStr.isBlank() || !pageStr.matches("[0-9]?[0-9]")) {
            matches = matchesService.getMatchesByOffsetAndLimit();
            req.setAttribute("page", 1);
        } else {
            //todo make button previous disabled when it is first page
            int page = validatePageNumber(Integer.parseInt(req.getParameter("page")));
            matches = matchesService.getMatchesByOffsetAndLimit(page);
            req.setAttribute("page", page);
        }
        req.setAttribute("matches", matches);
        req.getRequestDispatcher("/matches.jsp").forward(req, resp);
    }

    private int validatePageNumber(int pageNumber) {
        return pageNumber < 1 ? 1 : pageNumber;
    }
}