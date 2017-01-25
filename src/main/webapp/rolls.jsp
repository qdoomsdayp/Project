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
    <title>Роли</title>
    <style>
        .main{
            width: 1000px;
            margin: 0 auto;
        }
        .list,.Delete,.Edit,.Input{
            display: inline-block;
        }

    </style>
</head>

<body onload="OutputValues()">
<form name = "rolls" action="Rolls.java" method="post">
    <input type="hidden" id="command" value="" name = "command">
    <div class = "main">
        <h1>
            Роли
        </h1>
        <div class = "list">
        <select name = "Rolls">
            <option value = "rolls" selected>Выберете роль</option>
        </select>
        </div>
<div class = "Input">


            <input  type="submit" name = "Input" value="Добавить" onclick="document.getElementById('command').value = 'Input';
            document.rolls.submit(); " >


</div>
        <div class = "Edit">

            <input  type="submit" name = "Edit" value="Редактировать" onclick="document.getElementById('command').value = 'Edit';
        document.rolls.submit(); " >

        </div>
        <div class = "Delete">

            <input  type="submit" name = "Delete" value="Удалить" onclick="document.getElementById('command').value = 'Delete';
            document.rolls.submit(); " >

        </div>
        <div>
            <table>
                <tr>
                    <td>ИД роли</td>
                    <td><input type = "text"></td>
                </tr>
                <tr>
                    <td>имя роли</td>
                    <td><input type = "text"></td>
                </tr>
            </table>
        </div>

    </div>

</form>

<script type = "text/javascript">
    function getValuesByValue(index){

        return index.split(","); // преобразуем строку в массив значений
    }

    function OutputValues() {



        aCurrValues = getValuesByValue("<%=request.getAttribute("data")%>");
        var nCurrValuesCnt = aCurrValues.length;
        var oListL = document.forms["rolls"].elements["Rolls"];

        var oListOptionsCnt = oListL.options.length;
        oListL.length = 0; // удаляем все элементы из списка значений
        for (i = 0; i < nCurrValuesCnt; i++) {
            // далее мы добавляем необходимые значения в список
            if (document.createElement) {
                var newListOption = document.createElement("OPTION");
                newListOption.text = aCurrValues[i];
                newListOption.value = aCurrValues[i];
                // тут мы используем для добавления элемента либо метод IE, либо DOM
                (oListL.options.add) ? oListL.options.add(newListOption) : oListL.add(newListOption, null);
            } else {
                // для NN3.x-4.x
                oListL.options[i] = new Option(aCurrValues[i], aCurrValues[i], false, false);
            }
        }
    }

</script>
</body>
</html>
