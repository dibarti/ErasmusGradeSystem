<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>Teacher - Activities</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
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
    </div>
</nav>
<div class="container">
    <form>
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Firsname</th>
                <th scope="col">Lastname</th>
                <th scope="col">Email</th>
                <th scope="col" style="width: 15%">Danish grade</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${students}" var="activity">
                <tr>
                    <th scope="row">${activity.student.userId}</th>
                    <td>${activity.student.name}</td>
                    <td>${activity.student.surname}</td>
                    <td><a href="mailto:${activity.student.email}">${activity.student.email}</a></td>
                    <td align="center">
                        <c:choose>
                            <c:when test="${activity.grade == null}">
                                <input type="text" placeholder="Grade" id="${activity.student.userId}"
                                       name="studentId" style="width: 100%"
                                       data-container="body" data-toggle="popover" data-placement="top"
                                       title="Only Danish grades are allowed"
                                       data-content="Danish grades: 12, 10, 7, 4, 2, 0, -3"/>
                            </c:when>
                            <c:otherwise>
                                <b>${activity.grade}</b> <a href="#" class="edit"
                                                            id="${activity.student.userId}">Edit</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <input id="crsf" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </tbody>
        </table>
    </form>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script type="text/javascript">

    function closePopup(textField) {
        var id = $("#" + textField.id);
        id.removeClass("is-invalid");
        id.popover('hide');
    }

    function isDanishGrade(textField) {
        var grade = parseInt(textField.value);

        var danishGrades = [12, 10, 7, 4, 2, 0, -3];

        for (var j = 0; j < danishGrades.length; j++) {
            if (danishGrades[j] === grade) {
                console.log("Grade found, " + grade);
                return grade;
            }
        }
        textField.value = '';
        var id = $("#" + textField.id);
        id.focus();
        id.addClass("is-invalid");
        id.popover('show');
        return null;
    }

    function saveGrade(textfield) {
        closePopup(textfield);

        if (!textfield.value) {
            console.log("Value is empty!");
            return;
        }

        grade = isDanishGrade(textfield);
        if (grade == null) return;
        var formData = {
            'studentId': parseInt(textfield.id),
            'grade': grade,
            'activityId': ${activityId}
        };
        console.log(JSON.stringify(formData));

        $.ajax({
            url: '${activityId}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(formData),
            success: function () {

                var id = "#" + textfield.id;
                console.log($(id));
                $(id).parent().append('<b>' + grade + '</b> <a href="#" class="edit" id="' + textfield.id + '">Edit</a>');
                $(id).remove();
            },
            error: function (e) {
                console.log("ERROR: ", e);
            },
        })
    }

    $(document).ready(function () {

        // $(".edit").click(function () {
        //
        // });

        $(document).on("blur", "input[name=studentId]", function (event) {
            saveGrade(this);
        });

        $(document).on("click", ".edit", function (event) {
            console.log(this);
            var editId = "#" + this.id;
            console.log($(".edit" + editId));
            $(".edit" + editId).parent().replaceWith(
                '<td align="center"><input type="text" placeholder="Grade" ' +
                'id="' + this.id + '" name="studentId" style="width: 100%" data-container="body" ' +
                'data-toggle="popover" data-placement="top" title="Only Danish grades are allowed" ' +
                'data-content="Danish grades: 12, 10, 7, 4, 2, 0, -3"/></td>'
            );
            $(editId).focus();
        });

        $("input[name=studentId]").blur(function (event) {
            saveGrade(this);
        });

        $("input[name=studentId]").on("change paste keyup", function () {
            closePopup(this);
        });

        $(window).keydown(function (event) {
            var returnKey = 13;
            if (event.keyCode == returnKey) {
                // saveGrade(event.target);
                event.preventDefault();
                return false;
            }
        });
    });
</script>
</body>
</html>
