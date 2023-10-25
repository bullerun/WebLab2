<%@ page import="nikita.ryskal.modul.Point" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title> Lab1 Рыскаль Никита </title>
  <link rel="stylesheet" type="text/css" href="css/main.css">
  <!--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">-->
  <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>
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
<table class="body">

  <tr>
    <td class="graph">
      <canvas id="coordinates"></canvas>
    </td>
    <td class="columnWithData">
      <div class="coordinates-form">
        <div class="checkboxes">
          X coordinates: <span id="x"></span>
          <div><input type="checkbox" id="checkbox-5" name="checkbox" value="-5"/>
            <label for="checkbox-5">-5</label>
          </div>
          <div><input type="checkbox" id="checkbox-4" name="checkbox" value="-4"/>
            <label for="checkbox-4">-4</label>
          </div>
          <div><input type="checkbox" id="checkbox-3" name="checkbox" value="-3"/>
            <label for="checkbox-3">-3</label>
          </div>
          <div><input type="checkbox" id="checkbox-2" name="checkbox" value="-2"/>
            <label for="checkbox-2">-2</label>
          </div>
          <div><input type="checkbox" id="checkbox-1" name="checkbox" value="-1"/>
            <label for="checkbox-1">-1</label>
          </div>
          <div><input type="checkbox" id="checkbox0" name="checkbox" value="0">
            <label for="checkbox0">0</label>
          </div>
          <div><input type="checkbox" id="checkbox1" name="checkbox" value="1"/>
            <label for="checkbox1">1</label>
          </div>
          <div><input type="checkbox" id="checkbox2" name="checkbox" value="2"/>
            <label for="checkbox2">2</label>
          </div>
          <div><input type="checkbox" id="checkbox3" name="checkbox" value="3"/>
            <label for="checkbox3">3</label>
          </div>
        </div>
        <div><label for="inputText">Y coordinates:</label><input type="text" id="inputText"
                                                                 placeholder="(-5, 3)"/>
        </div>
        <div class="radios">
          R value:
          <input type="checkbox" id="radio1" value="1" name="radiobutton"><label
                for="radio1">1</label>
          <input type="checkbox" id="radio1-5" value="2" name="radiobutton"><label for="radio1-5">2</label>
          <input type="checkbox" id="radio2" value=3 name="radiobutton"><label for="radio2">3</label>
          <input type="checkbox" id="radio2-5" value="4" name="radiobutton"><label for="radio2-5">4</label>
          <input type="checkbox" id="radio3" value="5" name="radiobutton"><label for="radio3">5</label>
        </div>
        <button type="submit" class="checkButton" id="submitButton">Submit</button>
      </div>
    </td>
    <td >
      <div class="scroll">
        <table class="frontTables" >
          <thead>
          <tr >
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Results</th>
          </tr>
          </thead>
          <tbody id="result">
          <% List<Point> list = (List<Point>) request.getSession().getAttribute("array");
            if (list != null) {
          %>

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


          <%}%> </tbody>
        </table>
      </div>
    </td>
  </tr>
</table>
<script src="js/CoordinatePlane.js"></script>
<script src="js/form.js"></script>
</body>
</html>