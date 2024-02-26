<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Matches</title>
    <style>
        <%@include file="/pages/css/mainPage.css" %>
        <%@include file="/pages/css/matches.css" %>
    </style>
</head>
<body>
<header class="header">
    <div>
        <h1 class="mint"><a href="http://localhost:8080/main-page"><img src="../img/tennis_racket.png"
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

<main>
    <div class="table">
        <h1 class="sign">Finished Matches</h1>
        <div class="head">
            <form method="POST" action="/matches">
                <label for="namePlayer">Name: </label>
                <input id="namePlayer" type="text">
                <button class="button button-search" type="submit">Search</button>
                <button class="button button-clear" type="submit">Clear</button>
            </form>
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
</body>
</html>
