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
    <script src="user.js"></script>
    <meta cgarset="utf-8">
    <link rel="stylesheet" href="us.css" type="text/css">
    <title>Пользователи</title>

</head>

<body onload="OutputValuesRoll('<%=request.getAttribute("nameR")%>');
        downloadTable('<%=request.getAttribute("table")%>');">


<form name="users" action="Users.java" method="post">
    <input type="hidden" name="nev" id="nev">

    <div class="main">
        <div class="UP">
            <h1>
                Пользователи
            </h1>
            <a href="http://localhost:8080/rolls">Роли</a>
        </div>
        <div class="block1">
            <table id="myTable" class="table1">
                <tr class="table1 rows">
                    <th style="visibility: hidden"></th>
                    <th class="table1">Имя пользователя</th>
                    <th class="table1">Электронная почта</th>
                    <th class="table1">Роль пользователя</th>
                </tr>
            </table>

        </div>
        <div class="block2">
            <table id="Table" class="table2">
                <tr>
                    <td>имя пользователя</td>
                    <td><input type="text" id="Tname" name="nameUser"></td>
                    <td><input type="button" id="but_user" name="Input" value="Добавить" onclick="add()">
                    </td>
                </tr>
                <tr>
                    <td>E-mail пользователя</td>
                    <td><input type="text" name="email" id="Temail"></td>
                </tr>
                <tr>
                    <td>Роль</td>
                    <td>
                        <select name="Roll" id="Tidroll">
                            <option value="rol" id="option" selected>роль</option>
                        </select>
                    </td>
                </tr>
            </table>
        </div>

    </div>


</form>


</body>

</html>
