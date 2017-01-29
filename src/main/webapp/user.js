function del(index) {

    $.ajax({
        type: "POST",
        url: "DataBase",
        data: {type: "delete", nameS: document.getElementById("myTable").rows[index].cells[0].innerHTML},

        async: false,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            //our country code was correct so we have some information to display
            $("#Tname").val("");
            $("#Temail").val("");


        },
        error: function (data) {
            alert(data);
        }
    })
}
function deleteRow(obj) {
    var index = obj.parentNode.parentNode.rowIndex;
    var table = document.getElementById("myTable");
    del(index);
    table.deleteRow(index);

}
function add() {
    $.ajax({
        type: "POST",
        url: "DataBase",
        data: {
            type: "add", name: document.getElementById("Tname").value,
            email: document.getElementById("Temail").value,
            idroll: document.getElementById("Tidroll").value
        },
        async: false,
        dataType: "json",

        //if received a response from the server
        success: function (data, textStatus, jqXHR) {
            $("#Tname").val("");
            $("#Temail").val("");


            addTable(data.infoU);


        },
        error: function (data) {
            alert(data);
        }
    })
}
function addTable(stroka) {
    aCurrValues = getValuesByValue(stroka);
    var cells = 5;
    var rows = 1;
    var table = document.getElementById("myTable");
    for (var j = 0; j < rows; j++) {
        var row = document.createElement("tr");
        row.setAttribute('class', 'table1 rows');
        for (var i = 0; i < cells; i++) {
            if (i < 4) {
                var cell = document.createElement("td");
                cell.setAttribute('class', 'table1');
                if (i == 0) {
                    cell.setAttribute('style', 'visibility: hidden');
                }
                var cellText = document.createTextNode(aCurrValues[i]);
                cell.appendChild(cellText);
                row.appendChild(cell);
            } else {
                var cell = document.createElement("td");
                cell.setAttribute('class', 'table1');
                var btn = document.createElement('input');
                btn.type = 'button';
                btn.value = 'R';
                btn.setAttribute('onclick', 'editRow(this)');
                var btn1 = document.createElement('input');
                btn1.type = 'button';
                btn1.value = '[x]';
                btn1.setAttribute('onclick', 'deleteRow(this)');
                cell.appendChild(btn);
                cell.appendChild(btn1);
                row.appendChild(cell);
            }
        }
        table.appendChild(row);
    }
}
function downloadTable(stroka) {
    aCurrValues = getValuesByValue(stroka);
    var cells = 5;
    var rows = aCurrValues.length / (cells - 1);
    var cnt = 0;
    var table = document.getElementById("myTable");
    for (var j = 0; j < rows; j++) {
        var row = document.createElement("tr");
        row.setAttribute('class', 'table1 rows');
        for (var i = 0; i < cells; i++) {
            if (i < 4) {
                var cell = document.createElement("td");
                cell.setAttribute('class', 'table1');
                var cellText = document.createTextNode(aCurrValues[cnt]);
                if (i == 0) {
                    cell.setAttribute('style', 'visibility: hidden');
                }
                cell.appendChild(cellText);
                row.appendChild(cell);
                cnt++;
            } else {
                var cell = document.createElement("td");
                cell.setAttribute('class', 'table1');
                var btn = document.createElement('input');
                btn.type = 'button';
                btn.value = 'R';
                btn.setAttribute('onclick', 'editRow(this)');
                var btn1 = document.createElement('input');
                btn1.type = 'button';
                btn1.value = '[x]';
                btn1.setAttribute('onclick', 'deleteRow(this)');
                cell.appendChild(btn);
                cell.appendChild(btn1);
                row.appendChild(cell);
            }
        }
        table.appendChild(row);
    }
}
function editRow(obj) {
    var index = obj.parentNode.parentNode.rowIndex;
    document.getElementById("nev").value = index;
    if (document.getElementById("Tname").value=="" & document.getElementById("Temail").value=="") {
        var table = document.getElementById("Table");
        var row = document.createElement("tr");
        var cell = document.createElement("td");
        var btn = document.createElement('input');
        btn.type = 'button';
        btn.value = 'Сохранить';
        btn.setAttribute('onclick', 'edit(document.getElementById("nev").value)');
        cell.appendChild(btn);
        row.appendChild(cell);
        table.appendChild(row);
    }
    var table = document.getElementById("myTable");
    document.getElementById("Tname").value = table.rows[index].cells[1].innerHTML;
    document.getElementById("Temail").value = table.rows[index].cells[2].innerHTML;
    document.getElementById("Tidroll").value = table.rows[index].cells[3].innerHTML;


}
function edit(index) {
    $.ajax({
        type: "POST",
        url: "DataBase",
        data: {
            type: "edit", nameS: document.getElementById("myTable").rows[index].cells[0].innerHTML,
            nameI: document.getElementById("Tname").value,
            emailI: document.getElementById("Temail").value,
            idrollI: document.getElementById("Tidroll").value,
        },
        async: false,
        dataType: "json",
        success: function (data, textStatus, jqXHR) {
            $("#Tname").val("");
            $("#Temail").val("");
            var str = getValuesByValue(data.dataU);
            document.getElementById("myTable").rows[index].cells[1].innerHTML = str[0];
            document.getElementById("myTable").rows[index].cells[2].innerHTML = str[1];
            document.getElementById("myTable").rows[index].cells[3].innerHTML = str[2];
            document.getElementById("Table").deleteRow(3);
        },
        error: function (data) {
            alert(data);
        }
    })
}
function getValuesByValue(index) {

    return index.split(","); // преобразуем строку в массив значений
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




