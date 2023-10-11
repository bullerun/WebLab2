var button = document.getElementById('submitButton')

button.onclick = handleButtonClick;
table = document.getElementById("result");

function handleButtonClick() {
    let flag = true;
    let checkbox = document.querySelectorAll('input[name=checkbox]');
    let countCheckBox = 0;
    for (let i = 0; i < checkbox.length; i++) {
        let check = checkbox[i];
        if (check.checked) {
            var x = check.value;
            countCheckBox++;
        }
    }

    if (countCheckBox !== 1) {
        flag = false;
        alert("Put one check mark for the x coordinate")
    }

    let coordinatesY = document.getElementById("inputText").value;
    if (coordinatesY !== '') {
        let checkY = Number(coordinatesY)
        if (!isNaN(checkY)) {
            if (3 > checkY && checkY > -5) {
                var y = checkY;
            } else {
                flag = false;
                alert("y belongs to (-5; 3)")
                //Введите только цифру в промежутке от (-5;3)
            }
        } else {
            flag = false;
            alert("y should contain only digits")
            // игрик должен содержать только цифры
        }

    } else {
        flag = false;
        alert("y cannot be empty")
        // игрик не может быть пустым
    }
    let rBox = document.querySelectorAll('input[name=radiobutton]');
    let countR = 0;
    for (let i = 0; i < rBox.length; i++) {
        let check = rBox[i];
        if (check.checked) {
            var r = check.value;
            countR++;
        }
    }

    if (countR !== 1) {
        flag = false;
        alert("Put one check mark for the r coordinate")
    }
    if (flag) {
        rValue = parseInt(r)
        localStorage.setItem("rValue", rValue)
        flag = false
        send(x, y, rValue);
        redrawGraph(rValue)
        redrawPoint()
    }
}

async function send(x, y, r) {
    console.log("SENDING");
    const form = $('<form>', {
        action: "controller",
        method: "post"
    });
    const args = {X: x, Y: y, R: r};
    Object.entries(args).forEach(entry => {
        const [key, value] = entry;
        $('<input>').attr({
            type: "hidden",
            name: key,
            value: value
        }).appendTo(form);
    });
    form.appendTo('body').submit()
    console.log(form)

    // var dataToSend = {"X": x, "Y": y, "R": r, "time_zone_offset": new Date().toTimeString()}
    // $.ajax({
    //     type: "POST",
    //     url: "controller",
    //     data: dataToSend
    // });
    // const form = $('<form>', {
    //     action: "controller",
    //     method: "POST"
    // });
    // const args = {action: "submitForm", X: x, Y: y, R: r};
    // Object.entries(args).forEach(entry => {
    //     const [key, value] = entry;
    //     $('<input>').attr({
    //         type: "hidden",
    //         name: key,
    //         value: value
    //     }).appendTo(form);
    // });
    // // const w = window.open("about:blank", "_self", "noreferrer");
    // // w.document.write(form[0].outerHTML);
    // // w.document.forms[0].submit();
    // form.appendTo('body').submit()
    // const form = new FormData();
    // form.append("X", x);
    // form.append("Y", y);
    // form.append("R", r);
    //
    // const url = "controller?" + new URLSearchParams(form).toString();
    // const response = await fetch(url, {method: "post"});
}

async function sendWithOutRedirect(x, y, r) {
    const dataToSend = {"X": x, "Y": y, "R": r};
    console.log("SENDING_POINT");
    const response = await fetch("controller", {
        method: "POST",
        body: JSON.stringify(dataToSend),
    });
    const data = await response.json();
    console.log(typeof data.result)
    drawPoint(x, y, data.result)
    addToTable(x, y, r, data.result)
}

function addToTable(x, y, r, result) {
    const table = document.getElementById("result");
    const newRow = table.insertRow(0);
    newRow.insertCell().innerText = x;
    newRow.insertCell().innerText = y;
    newRow.insertCell().innerText = r;
    newRow.insertCell().innerHTML = result
}

document.addEventListener("DOMContentLoaded", () => {
    if (!localStorage.getItem('rValue')) {
        localStorage.setItem('rValue', "R")
        rValue = "R"
    } else {
        rValue = localStorage.getItem('rValue')
    }
    redrawGraph(rValue)
    redrawPoint()

});
function redrawPoint(){
    const table = document.getElementById("result");
    if (table) {
        for (let item of table.rows) {
            const x = parseFloat(item.children[0].innerText.trim());
            const y = parseFloat(item.children[1].innerText.trim());
            const r = parseFloat(item.children[2].innerText.trim());
            if (isNaN(x) || isNaN(y) || isNaN(r)) continue;
            drawPoint(x, y, item.children[3].innerText.trim() === "true");
        }
    }
}
$('input[name="radiobutton"]').on('change', function () {
    let rBox = document.querySelectorAll('input[name=radiobutton]');
    let countR = 0;
    for (let i = 0; i < rBox.length; i++) {
        let check = rBox[i];
        if (check.checked) {
            var r = check.value;
            countR++;
        }
    }
    if (countR === 1) {
        rValue = parseInt(r)
        localStorage.setItem("rValue", rValue)
        redrawGraph(rValue)
        redrawPoint()
    }


});