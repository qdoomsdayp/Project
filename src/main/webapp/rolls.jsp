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
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="rol.js"></script>
    <meta cgarset="utf-8">
    <link rel="stylesheet" href="rol.css" type="text/css">
    <title>Роли</title>

</head>

<body onload="OutputValues('<%=request.getAttribute("data")%>')">
<form name="rolls" action="Rolls.java" method="post">
    <input type="hidden" id="command" value="" name="command">
    <div class="main">
        <h1>
            Роли
        </h1>
        <div class="list">
            <select name="Rolls" id="id_Rolls" onchange="selectList()">
                <option value="rolls" selected>Выберете роль</option>
            </select>
        </div>
        <div class="Input">


            <input type="button" name="Input" value="Добавить" onclick="add()">


        </div>
        <div class="Edit">

            <input type="button" name="Edit" value="Редактировать" onclick="edit()">

        </div>
        <div class="Delete">

            <input type="button" name="Delete" value="Удалить" onclick="del()">

        </div>
        <div>
            <table>
                <tr>
                    <td>имя роли</td>
                    <td><input type="text" id="Tnamer" name="idroll"></td>
                </tr>
            </table>
        </div>
        <a href="http://localhost:8080/us">Пользователи</a>

    </div>

</form>

</body>
</html>
