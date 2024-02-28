<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.UUID" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match Finished</title>
    <link rel="shortcut icon" href="../img/tennis_ball.png"/>
    <style>
        <%@include file="/pages/css/mainPage.css" %>
        <%@include file="/pages/css/match.css" %>
    </style>
</head>
<body>
<% UUID matchId = UUID.fromString(request.getParameter("uuid")); %>
<header class="header">
    <div><h1 class="mint"><a href="http://localhost:8080/main-page"><img src="../img/tennis_racket.png"
                                                                         alt="tennis racket">Tennis Scoreboard</a></h1>
    </div>
    <div><h1 class="ordinary"><a href="http://localhost:8080/new-match">New Match</a></h1></div>
    <div><h1 class="ordinary"><a href="http://localhost:8080/matches">Matches</a></h1></div>
    <div><h1 class="ordinary"><%
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, HH:mm", Locale.ENGLISH);
        out.print(LocalDateTime.now().format(formatter));
    %>
    </h1></div>
</header>
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

<main>

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
</body>
</html>