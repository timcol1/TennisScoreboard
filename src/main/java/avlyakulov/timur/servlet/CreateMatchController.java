package avlyakulov.timur.servlet;

import avlyakulov.timur.service.CreateMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;


@Slf4j
@WebServlet(urlPatterns = "/new-match")
public class CreateMatchController extends HttpServlet {
    private CreateMatchService createMatchService;

    @Override
    public void init() throws ServletException {
        createMatchService = new CreateMatchService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/newMatch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerOneName = req.getParameter("player1");
        String playerTwoName = req.getParameter("player2");
        log.info("We got a request to begin a match with the first player " + playerOneName + " and the second player " + playerTwoName);

        UUID matchId = createMatchService.createMatch(playerOneName, playerTwoName);
        log.info("We created match in progress with such id {}", matchId);

        resp.sendRedirect("/TennisScoreboard-1.0/match-score?uuid=" + matchId);
    }
}