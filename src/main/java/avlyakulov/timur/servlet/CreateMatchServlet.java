package avlyakulov.timur.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Slf4j
@WebServlet(urlPatterns = "/createMatch")
public class CreateMatchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String player1 = req.getParameter("player1");
        String player2 = req.getParameter("player2");
        log.info("We got a request to begin a match with the first player " + player1 + " and the second player " + player2);

        req.setAttribute("player1", player1);
        req.setAttribute("player2", player2);
        req.getRequestDispatcher("/match").forward(req, resp);

    }
}