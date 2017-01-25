<%--
  Created by IntelliJ IDEA.
  User: wital
  Date: 25.01.2017
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta cgarset = "utf-8">
    <link rel="stylesheet" href="/roll.css" type="text/css">
    <title>Страница</title>
</head>

<body>
<h1 class="ttt">
    Роли
</h1>
    <div class = "main">
        <div class = "list">
        <select name = "Rolls">
            <option value = "rolls" selected>Выберете роль</option>
        </select>
        </div>
<div class = "Input">
        <form action="Users.java" method="post">

            <input  type="submit" name = "Input" value="Добавить">

        </form>
</div>
        <div class = "Edit">
        <form action="Users.java" method="post">

            <input  type="submit" name = "Edit" value="Редактировать">

        </form>
        </div>
        <div class = "Delete">
        <form action="Users.java" method="post">

            <input  type="submit" name = "Delete" value="Удалить">

        </form>
        </div>
    </div>





</body>
</html>
