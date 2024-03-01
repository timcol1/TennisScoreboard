<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="eu">
<head>
    <title>New Match</title>
    <link rel="shortcut icon" href="https://cdn-icons-png.flaticon.com/512/889/889494.png"/>
    <style>
        <%@include file="/pages/css/mainPage.css" %>
        <%@include file="/pages/css/newMatch.css" %>
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
    <form method="POST" action="/TennisScoreboard-1.0/new-match">
        <div class="wrapper">
            <div class="player">
                <label for="Player1"><b>Enter Player 1 name</b>
                    <input id="Player1" name="player1" type="text" required>
                </label>
            </div>
            <div class="sign">
                <div>
                    <p><b>New Match</b></p>
                </div>
                <button type="submit" onclick="validateForm()"><b>START</b></button>
            </div>
            <div class="player">
                <label for="Player2"><b>Enter Player 2 name</b>
                    <input id="Player2" name="player2" type="text" required>
                </label>
            </div>
        </div>
    </form>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/pages/js/validateFormCreateMatch.js"></script>
</body>
</html>
