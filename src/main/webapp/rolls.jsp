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
    <script type = "text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
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

<body onload="OutputValues('<%=request.getAttribute("data")%>')">
<form name = "rolls" action="Rolls.java" method="post">
    <input type="hidden" id="command" value="" name = "command">
    <div class = "main">
        <h1>
            Роли
        </h1>
        <div class = "list">
        <select name = "Rolls" id = "id_Rolls" onchange="selectList()">
            <option value = "rolls" selected>Выберете роль</option>
        </select>
        </div>
<div class = "Input">


            <input  type="button" name = "Input" value="Добавить" onclick="check(this.form)" >


</div>
        <div class = "Edit">

            <input  type="button" name = "Edit" value="Редактировать" onclick="edit()" >

        </div>
        <div class = "Delete">

            <input  type="button" name = "Delete" value="Удалить" onclick="del()" >

        </div>
        <div>
            <table>
                <tr>
                    <td>имя роли</td>
                    <td><input type = "text" id = "Tnamer" name = "idroll"></td>
                </tr>
            </table>
        </div>
        <a href = "http://localhost:8080/">Пользователи</a>

    </div>

</form>

<script type = "text/javascript">




    function selectList() {

        $.ajax({
            type: "POST",
            url: "DataBase",
            data: {type: "selectListR",name: document.getElementById("id_Rolls").value, id: '123'},
            async: false,
            dataType: "json",

            //if received a response from the server
            success: function( data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display

                $("#Tnamer").val(data.nam);


            },
            error: function (data) {
                alert(data);
            }
        })
    }
    function del() {

        $.ajax({
            type: "POST",
            url: "DataBase",
            data: {type: "deleter",nameS: document.getElementById("id_Rolls").value},

            async: false,
            dataType: "json",

            //if received a response from the server
            success: function( data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display
                $("#Tnamer").val("");
                OutputValues(data.listName);

            },
            error: function (data) {
                alert(data);
            }
        })
    }
    function edit() {
        $.ajax({
            type: "POST",
            url: "DataBase",
            data: {type: "editr",nameS: document.getElementById("id_Rolls").value,
            nameI: document.getElementById("Tnamer").value},

            async: false,
            dataType: "json",

            //if received a response from the server
            success: function( data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display

                $("#Tnamer").val("");
                OutputValues(data.listName);

            },
            error: function (data) {
                alert(data);
            }
        })
    }
    function add() {

        $.ajax({
            type: "POST",
            url: "DataBase",
            data: {type: "addr",name: document.getElementById("Tnamer").value},
            async: false,
            dataType: "json",

            //if received a response from the server
            success: function( data, textStatus, jqXHR) {
                $("#Tnamer").val("");
                OutputValues(data.listName);



            },
            error: function (data) {
                alert(data);
            }
        })
    }

    function getValuesByValue(index){

        return index.split(","); // преобразуем строку в массив значений
    }

    function OutputValues(stroka) {



        aCurrValues = getValuesByValue(stroka);
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


    function showError(container, errorMessage) {
        container.className = 'error';
        var msgElem = document.createElement('span');
        msgElem.className = "error-message";
        msgElem.innerHTML = errorMessage;
        container.appendChild(msgElem);
    }

    function resetError(container) {
        container.className = '';
        if (container.lastChild.className == "error-message") {
            container.removeChild(container.lastChild);
        }
    }

    function check(form) {
        var elems = form.elements;

        resetError(elems.idroll.parentNode);

        if (!elems.idroll.value) {
            showError(elems.idroll.parentNode, ' не ввели');
        }else{
            add();
        }

    }


</script>
</body>
</html>
