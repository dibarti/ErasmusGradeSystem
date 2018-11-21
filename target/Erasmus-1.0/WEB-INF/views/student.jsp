<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>Student - grades</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
    <style>
        input[type="radio"] {
            display: none;
        }

        .btn-default.btn-on:not(.active) {
            background-color: darkgray;
            color: white;
        }

        .btn-default.btn-off:not(.active) {
            background-color: darkgray;
            color: white;
        }

        .btn-default.btn-on.active {
            background-color: #5BB75B;
            color: white;
        }

        .btn-default.btn-off.active {
            background-color: #5BB75B;
            color: white;
        }
    </style>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<nav class="navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="<c:url value="/logout" />">Logout</a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <a href="#" class="nav-item active nav-link">${user}</a>
        </ul>

        <div class="navnar-nav">
            <form id="gradeButton" action="/student">
                <label for="status" style="margin-right: 10px; color: white;"><span>Format</span></label>
                <div class="btn-group" id="status" data-toggle="buttons">
                    <label class="btn btn-default btn-on btn-xs <c:if test = "${format == 'danish'}"><c:out value = "active"/></c:if>">
                        <input type="radio" value="danish" name="format" checked="checked"
                               onchange="submitForm()">DANISH</label>
                    <label class="btn btn-default btn-off btn-xs <c:if test = "${format == 'ects'}"><c:out value = "active"/></c:if>">
                        <input type="radio" value="ects" name="format" onchange="submitForm()">ECTS</label>
                </div>
            </form>
        </div>
    </div>
</nav>
<div class="container">
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Exam</th>
            <th scope="col">Date</th>
            <th scope="col">Grade</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${grades}" var="grade" varStatus="counter">
            <tr>
                <th scope="row">${counter.count}</th>
                <td>${grade.course.name}</td>
                <td align="center">${grade.date}</td>
                <td align="center">${grade.grade}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function submitForm() {
        document.getElementById("gradeButton").submit();
    }
</script>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
</body>
</html>
