<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Match</title>
    <link rel="shortcut icon" href="../img/tennis_ball.png"/>
    <style>
        <%@include file="/pages/css/mainPage.css" %>
        <%@include file="/pages/css/match.css" %>
    </style>
</head>
<body>
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
<main>
    <table>
        <thead>
        <tr>
            <th>CODE</th>
            <th>STOCK</th>
            <th>CAP</th>
            <th>INCH</th>
            <th>BOX TYPE</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>CES-9000</td>
            <td>50mt</td>
            <td>9mm</td>
            <td>1/2"</td>
            <td>Kangal / Coil</td>
        </tr>
        <tr>
            <td>CES-9000</td>
            <td>50mt</td>
            <td>9mm</td>
            <td>1/2"</td>
            <td>Kangal / Coil</td>
        </tr>
        <tr>
            <td>CES-9000</td>
            <td>50mt</td>
            <td>9mm</td>
            <td>1/2"</td>
            <td>Kangal / Coil</td>
        </tr>
        <tr>
            <td>CES-9000</td>
            <td>50mt</td>
            <td>9mm</td>
            <td>1/2"</td>
            <td>Kangal / Coil</td>
        </tr>
        </tbody>
    </table>
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