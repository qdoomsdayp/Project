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

    <meta cgarset="utf-8">
    <link rel="stylesheet" href="/roll.css" type="text/css">
    <title>Пользователи</title>
    <style>
        .main {
            width: 1000px;
            margin: 0 auto;
        }

        .list, .Delete, .Edit, .Input {
            display: inline-block;
        }

    </style>
</head>

<body onload="OutputValuesUser('<%=request.getAttribute("data")%>');
        OutputValuesRoll('<%=request.getAttribute("nameR")%>')">


<form name="users" action="Users.java" method="post">
    <input type="hidden" name="allId" id = "allId ">

    <div class="main">
        <h1>
            Пользователи
        </h1>
        <div class="list">
            <select name="Users" id="id_Users" onchange="selectList()">
                <option value="user" selected>Выберете пользователя</option>
            </select>
        </div>
        <div class="Input">


            <input type="button" id="but_user" name="Input" value="Добавить" onclick="check(this.form)">

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
                    <td>имя пользователя</td>
                    <td><input type="text" id="Tname" name="nameUser"></td>
                </tr>
                <tr>
                    <td>E-mail пользователя</td>
                    <td><input type="text" name="email" id="Temail"></td>
                </tr>
                <tr>
                    <td>Роль</td>
                    <td>
                        <select name="Roll" id="Tidroll" >
                            <option value="rol" id = "option" selected>роль</option>
                        </select>
                    </td>
                </tr>
            </table>
            <a href="http://localhost:8080/rolls">Роли</a>
        </div>
    </div>


</form>


<script type="text/javascript">


    function selectList() {

        $.ajax({
            type: "POST",
            url: "DataBase",
            data: {type: "selectList", name: document.getElementById("id_Users").value, id: '123'},
            async: false,
            dataType: "json",

            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display

                $("#Tname").val(data.nam);
                $("#Temail").val(data.emai);
                $("#Tidroll").val(data.idrol);
                $("#allId").val(data.allId);

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
            data: {type: "delete", nameS: document.getElementById("id_Users").value},

            async: false,
            dataType: "json",

            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display
                $("#Tname").val("");
                $("#Temail").val("");


                OutputValuesUser(data.listName);

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
            data: {
                type: "edit", nameS: document.getElementById("id_Users").value,
                nameI: document.getElementById("Tname").value,
                emailI: document.getElementById("Temail").value,
                idrollI: document.getElementById("Tidroll").value,
            },
            async: false,
            dataType: "json",

            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                //our country code was correct so we have some information to display

                $("#Tname").val("");
                $("#Temail").val("");

                OutputValuesUser(data.listName);

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
            data: {
                type: "add", name: document.getElementById("Tname").value,
                email: document.getElementById("Temail").value,
                idroll: document.getElementById("Tidroll").value},
            async: false,
            dataType: "json",

            //if received a response from the server
            success: function (data, textStatus, jqXHR) {
                $("#Tname").val("");
                $("#Temail").val("");

                OutputValuesUser(data.listName);


            },
            error: function (data) {
                alert(data);
            }
        })
    }

    function getValuesByValue(index) {

        return index.split(","); // преобразуем строку в массив значений
    }

    function OutputValuesUser(stroka) {


        aCurrValues = getValuesByValue(stroka);
        var nCurrValuesCnt = aCurrValues.length;
        var oListL = document.forms["users"].elements["Users"];

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
    function OutputValuesRoll(stroka) {


        aCurrValues = getValuesByValue(stroka);
        var nCurrValuesCnt = aCurrValues.length;
        var oListL = document.forms["users"].elements["Roll"];

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

        resetError(elems.nameUser.parentNode);
        resetError(elems.email.parentNode);


        if (!elems.nameUser.value) {
            showError(elems.nameUser.parentNode, ' Не верный ИД пользователя');
        } else if (!elems.email.value) {
            showError(elems.email.parentNode, ' Не верный ИД пользователя');
        }
        else {
            add();
        }
    }


</script>


</body>
</html>
