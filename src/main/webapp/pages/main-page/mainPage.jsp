<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tennis-ScoreBoard</title>
    <link rel="shortcut icon" href="../../img/tennis_ball.png"/>
    <style>
        <%@include file="/pages/main-page/mainPage.css" %>
    </style>
</head>
<body>
<header class="header">
    <div><h1 class="mint"><a href="http://localhost:8080/main-page"><img src="../../img/tennis_racket.png"
                                                                         alt="tennis racket">Tennis Scoreboard</a></h1>
    </div>
    <div><h1 class="ordinary"><a href="http://localhost:8080/new-match">New Match</a></h1></div>
    <div><h1 class="ordinary"><a href="http://localhost:8080/matches">Matches</a></h1></div>
    <div><h1 class="ordinary"><%
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, HH:mm", Locale.ENGLISH);
        out.print(currentDateTime.format(formatter));
    %>
    </h1></div>
</header>

</body>
</html>