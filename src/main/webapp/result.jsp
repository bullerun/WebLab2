<%@ page import="java.util.List" %>
<%@ page import="nikita.ryskal.modul.Point" %><%--
  Created by IntelliJ IDEA.
  User: nikitosek
  Date: 09.10.2023
  Time: 3:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Результат</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> Lab1 Рыскаль Никита </title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">-->
</head>
<body>
<table class="header">
    <thead>
    <tr>
        <th scope="col" class="numberLab">Lab1</th>
        <th scope="col">Рыскаль Никита</th>
        <th scope="col">P3218</th>
        <th scope="col">Вариант 2813</th>
    </tr>
    </thead>
</table>
<div class="result_width">
    <div class="scroll">
        <table class="frontTables">
            <thead>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Results</th>

            </tr>
            </thead>
            <% List<Point> list = (List<Point>) request.getSession().getAttribute("array");
                if (list != null) {
            %>
            <tbody id="result">
            <% for (Point point : list) { %>
            <tr>
                <td>
                    <%= point.getX() %>
                </td>
                <td>
                    <%= point.getY() %>
                </td>
                <td>
                    <%= point.getR() %>
                </td>
                <td>
                    <%= point.getHit() %>
                </td>
            </tr>
            <% } %>
            </tbody>

            <%}%>
        </table>
    </div>
</div>
<div class="result_width center" ><a href="${pageContext.request.contextPath}/controller" class="back_href">Pounce в окно</a></div>
</body>
<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
</html>
