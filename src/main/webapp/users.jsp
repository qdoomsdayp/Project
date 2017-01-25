<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta cgarset = "utf-8">
    <title>Пользователи</title>
    <link rel="stylesheet" href="user.css">
</head>
<body>
<h1>
    Пользователи
</h1>
<form target = "my" action="Users.java" method="post">
    <div>
        <p>Введите выражение </p>
        <input type = "text" name = "expression">
        <input  type="submit" name = "Input" value="Рассчитать">
    </div>
    <div>
        <p>Результат</p>
        <p>
            <%=
            request.getAttribute("rez")
            %>

        </p>
    </div>


</form>


</body>
</html>

