var col = "X";
let res = ["none", "none", "none", "none", "none", "none", "none", "none", "none"]

function turn(number) {
    res[number - 1] = col;
    $('#s' + number).text(col);
    if (col === "X") {
        col = "O";
    } else {
        col = "X";
    }
    $('#s' + number).prop("disabled", true);
    console.log(chek());

    // alert( chek() );

}
function chek() {
    for (let i = 0; i < 2; i += 1) {
        if (res[0 + i] == res[3 + i] && res[3 + i] == res[6 + i] && res[6 + i] != "none") {

            return res[0 + i];



        }
    }
    for (let i = 0; i < 7; i += 3) {
        if (res[0 + i] == res[1 + i] && res[1 + i] == res[2 + i] && res[2 + i] != "none") {
            return res[0 + i];
        }
    }
    if (res[0] == res[4] && res[4] == res[8] && res[8] != "none") {
        return res[4];
    }
    if (res[2] == res[4] && res[4] == res[6] && res[6] != "none") {
        return res[4];
    }
    let a = false;
    for (let i = 0; i < 9; i++) {
        if (res[i] == "none") {
            a = true;
        }

    }
    if (a == false) {
        return "draw";
    } else {
        return "none";
    }


}