<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Locale" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matches</title>
    <style>
        <%@include file="/pages/css/mainPage.css" %>
        <%@include file="/pages/css/matches.css" %>
    </style>
    <meta name="viewport" content="width=device-width">
</head>
<body>
<header class="header">
    <div><img src="../img/tennis_racket.png" alt="tennis racket">
        <h1 class="mint"><a href="http://localhost:8080/main-page">Tennis Scoreboard</a></h1>
    </div>
    <div><h1 class="mint"><a href="http://localhost:8080/new-match">New Match</a></h1></div>
    <div><h1 class="mint"><a href="http://localhost:8080/matches">Matches</a></h1></div>
    <div><h1 class="mint"><%
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, HH:mm", Locale.ENGLISH);
        out.print(LocalDateTime.now().format(formatter));
    %>
    </h1></div>
</header>

<main>
    <div class="wrapper">
        <h1 class="sign">Finished Matches</h1>
        <div class="head">
            <form method="GET" action="/matches">
                <label for="namePlayer">Name: </label>
                <input type="hidden" name="page" value="${page}">
                <input id="namePlayer" name="filter_by_player_name" type="text" required>
                <button class="button button-search" type="submit">Search</button>
                <button class="button button-clear" type="button">Clear</button>
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
                <form method="GET" action="/matches">
                    <button class="button button-previous" id="butt-prev" name="page" value="${page - 1}">Previous
                    </button>
                    <button class="button button-page" disabled>${page}</button>
                    <button class="button button-next" id="butt-next" name="page" value="${page + 1}">Next</button>
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
        <a href="https://github.com/timcol1">Git</a>
        <a href="https://www.linkedin.com/in/timmawv/">Linkedin</a>
    </div>
</footer>
<script type="text/javascript" src="${pageContext.request.contextPath}/pages/js/buttonMatchesChecker.js"></script>
</body>
</html>