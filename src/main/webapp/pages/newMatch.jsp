<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="eu">
<head>
    <title>New Match</title>
    <link rel="shortcut icon" href="../img/tennis_ball.png"/>
    <style>
        <%@include file="/pages/css/mainPage.css" %>
        <%@include file="/pages/css/newMatch.css" %>
    </style>
    <meta name="viewport" content="width=1000">
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
    <form method="POST" action="/new-match">
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
        <a href="https://github.com/timcol1">Git</a>
        <a href="https://www.linkedin.com/in/timmawv/">Linkedin</a>
    </div>
</footer>
<script type="text/javascript" src="${pageContext.request.contextPath}/pages/js/validateFormCreateMatch.js"></script>
</body>
</html>
