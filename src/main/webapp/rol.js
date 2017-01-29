/**
 * Created by wital on 29.01.2017.
 */
function selectList() {

    $.ajax({
        type: "POST",
        url: "DataBase",
        data: {type: "selectListR", name: document.getElementById("id_Rolls").value, id: '123'},
        async: false,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
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
        data: {type: "deleter", nameS: document.getElementById("id_Rolls").value},

        async: false,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
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
        data: {
            type: "editr", nameS: document.getElementById("id_Rolls").value,
            nameI: document.getElementById("Tnamer").value
        },

        async: false,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
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
        data: {type: "addr", name: document.getElementById("Tnamer").value},
        async: false,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            $("#Tnamer").val("");
            OutputValues(data.listName);


        },
        error: function (data) {
            alert(data);
        }
    })
}
function getValuesByValue(index) {

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


