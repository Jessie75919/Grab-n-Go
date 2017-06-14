function doFirst() {
    alert("H");
    // console.log("gogoog");


}

function validateForm(event) {

    event.preventDefault(); // this will prevent the submit event.

    var formA = document.getElementById("theForm")
    var user = document.getElementById("usr");
    var usrRes = document.getElementById("usrRes");
    var pw = document.getElementById("pw");
    var pwRes = document.getElementById("pwRes");
    var signInBtn = document.getElementById("signInBtn");
    var hasErr = false;

    // validate username  
    if (!user.value) {
        usrRes.innerHTML = "<font color='blue' size='-2'>請輸入帳號</font>";
        hasErr = true;
    } else if (user.value.length > 30) {
        usrRes.innerHTML = "<font color='blue' size='-2'>帳號過長請重新輸入</font>";
        hasErr = true;
    }

    // validate password  
    if (!pw.value) {
        pwRes.innerHTML = "<font color='blue' size='-2'>請輸入密碼</font>";
        hasErr = true;
    }

    // if has any error.. return
    if (hasErr) {
        return false;
    } else {
        formA.submit();
    }
}




// // Sign In btn click
// signInBtn.onclick = function () {


//     // validate username  
//     if (!user.value) {
//         usrRes.innerHTML = "<font color='blue' size='-2'>請輸入帳號</font>";
//         hasErr = true;
//     } else if (user.value.length > 30) {
//         usrRes.innerHTML = "<font color='blue' size='-2'>帳號過長請重新輸入</font>";
//         hasErr = true;
//     }

//     // validate password  
//     if (!ps.value) {
//         usrRes.innerHTML = "<font color='blue' size='-2'>請輸入密碼</font>";
//         hasErr = true;
//     }

//     // if has any error.. return
//     if (hasErr) {
//         return false;
//     }

// }


window.addEventListener('load', doFirst, false);