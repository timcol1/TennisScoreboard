<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="java.time.Instant" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matches</title>
    <link rel="shortcut icon" href="https://cdn-icons-png.flaticon.com/512/889/889494.png"/>
    <style>
        <%@include file="/pages/css/mainPage.css" %>
        <%@include file="/pages/css/matches.css" %>
    </style>
    <meta name="viewport" content="width=1000">
</head>
<body>

<header class="header">
    <div><img src="https://cdn.icon-icons.com/icons2/3664/PNG/512/tennis_racket_ball_sport_game_icon_228593.png"
              alt="tennis racket">
        <h1 class="mint"><a href="/TennisScoreboard-1.0/main-page">Tennis Scoreboard</a></h1>
    </div>
    <div><h1 class="mint"><a href="/TennisScoreboard-1.0/new-match">New Match</a></h1></div>
    <div><h1 class="mint"><a href="/TennisScoreboard-1.0/matches">Matches</a></h1></div>
    <div><h1 class="mint"><%
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, HH:mm", Locale.ENGLISH);
        out.print(Instant.ofEpochMilli(System.currentTimeMillis())
                .atZone(ZoneId.of("Europe/Kiev")).toLocalDateTime().format(formatter));
    %>
    </h1></div>
</header>

<main>
    <div class="wrapper">
        <h1 class="sign">Finished Matches</h1>
        <div class="head">
            <form method="GET" action="/TennisScoreboard-1.0/matches">
                <label for="namePlayer">Name: </label>
                <input id="namePlayer" name="filter_by_player_name" type="text" required>
                <button class="button button-search" type="submit">Search</button>
            </form>
        </div>
        <div class="table">
            <div class="table head-main">
                <div class="table head">
                    <div class="id">ID</div>
                    <div class="player1">Player 1</div>
                    <div class="player2">Player 2</div>
                    <div class="winner">Winner</div>
                </div>
                <div class="table main">
                    <c:forEach var="match" items="${matches}">
                        <div class="id">${match.getId()}</div>
                        <div class="player1">${match.getPlayerOneName()}</div>
                        <div class="player2">${match.getPlayerTwoName()}</div>
                        <div class="winner">${match.getWinnerName()}</div>
                    </c:forEach>
                </div>
            </div>
            <div class="table footer">
                <form method="GET" action="/TennisScoreboard-1.0/matches">
                    <% String playerName = request.getParameter("filter_by_player_name");
                        if (playerName != null) {
                    %>
                    <input type="hidden" name="filter_by_player_name" value="<%= playerName %>">
                    <% } %>
                    <button class="button button-previous" id="butt-prev" name="page" value="${page - 1}"
                            <% String pageStr = request.getParameter("page");
                                if (pageStr != null) {
                                    int pageCurrent = Integer.parseInt(request.getParameter("page"));
                                    if (pageCurrent <= 1) { %>
                            disabled <% }
                    } else { %>
                            disabled
                            <% } %>
                    >Previous
                    </button>
                    <button class="button button-page" disabled>${page} / ${pages}</button>
                    <button class="button button-next" id="butt-next" name="page" value="${page + 1}"
                    ${page >= pages ? "disabled" : ""}>Next
                    </button>
                </form>
            </div>
        </div>
    </div>
</main>

<footer>
    <div class="first_block">
        <p>© 2024 Avlyakulov Timur</p>
    </div>
    <div class="sec_block">
        <a href="https://github.com/timmawv">Git</a>
        <a href="https://www.linkedin.com/in/timmawv/">Linkedin</a>
    </div>
</footer>
</body>
</html>