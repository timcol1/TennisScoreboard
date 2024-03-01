<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match Finished</title>
    <link rel="shortcut icon" href="https://cdn-icons-png.flaticon.com/512/889/889494.png"/>
    <style>
        <%@include file="/pages/css/mainPage.css" %>
        <%@include file="/pages/css/match.css" %>
    </style>
    <meta name="viewport" content="width=1000">
</head>
<body>
<header class="header">
    <div><img src="https://cdn.icon-icons.com/icons2/3664/PNG/512/tennis_racket_ball_sport_game_icon_228593.png" alt="tennis racket">
        <h1 class="mint"><a href="http://localhost:8080/TennisScoreboard-1.0/main-page">Tennis Scoreboard</a></h1>
    </div>
    <div><h1 class="mint"><a href="http://localhost:8080/TennisScoreboard-1.0/new-match">New Match</a></h1></div>
    <div><h1 class="mint"><a href="http://localhost:8080/TennisScoreboard-1.0/matches">Matches</a></h1></div>
    <div><h1 class="mint"><%
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, HH:mm", Locale.ENGLISH);
        out.print(LocalDateTime.now().format(formatter));
    %>
    </h1></div>
</header>

<main>
    <div class="wrapper">
        <div class="player player1">
            <div class="name">
                <h1>Player</h1>
                <h1>${match.getPlayerOneName()}</h1>
            </div>
            <div class="sets">
                <h1>Sets</h1>
                <h1>${match.getPlayerOneSet()}</h1>
            </div>
        </div>
        <div class="sign">
            <h1>Match finished! ${match.getWinnerName()} wins</h1>
        </div>
        <div class="player player2">
            <div class="sets">
                <h1>Sets</h1>
                <h1>${match.getPlayerTwoSet()}
                </h1>
            </div>
            <div class="name">
                <h1>Player</h1>
                <h1>${match.getPlayerTwoName()}
                </h1>
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