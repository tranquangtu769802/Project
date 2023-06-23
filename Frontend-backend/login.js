$(function(){

});

function login(){
    //lấy thông tin username và password
var userNameLogin = $("#inputUsername").val();
var passwordLogin = $("#inputPassword").val();

// đóng gói
var account = {
    userName: userNameLogin,
    password: passwordLogin,
};

$.ajax({
    type: "GET",
    url: "http://localhost:8075/api/v1/auth/login",
    dataType: "JSON",
    beforeSend: function (xhr) {
        xhr.setRequestHeader("Authorization", "Basic " + btoa(`${userNameLogin}:${passwordLogin}`));
    },
    success: function (response) {
        alert("Đăng nhập thành công")
        localStorage.setItem("accLogin", JSON.stringify(account)) // lưu tài khoản ở bộ nhớ localStorage
        window.open("./index.html", "_self")// _self là thuộc tính taget xác định địa chỉ đích liên kết,cửa sổ mở lên sẽ hiện ngay trên tab hiện tại
    },
    error: function(response){
        alert("Đăng nhập thất bại");
    }
});
}

// ham gui mail
function sendMail() {
    var v_email = $("#inputEmail").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8075/api/v1/accounts/sendMail?email=" + v_email,
        // data: "data",
        // dataType: "dataType",
        contentType: "application/json; charset=UTF-8",
        success: function (response) {
            alert("gui mail thanh cong")
        },
        error: function (response) {
            alert("gui mail khong thanh cong");
        },
    });
}

// ham thay doi password
function changePass() {
    var v_password = $("#inputPassword").val();

    // lay token tu password
    const queryString = window.location.search;
    console.log(queryString);
    const urlParams = new URLSearchParams(queryString);
    const token = urlParams.get('token');

    $.ajax({
        type: "PUT",
        url: `http://localhost:8075/api/v1/accounts/updatePassword?token=${token}&newPassword=${v_password}`,
        contentType: "application/json; charset=UTF-8",
        success: function (response) {
            alert("doi password thanh cong");
            window.open("./login.html", "_self");
        },
        error: function (response) {
            alert("doi password khong thanh cong");
        },

    });

}


function forgotPass() {
    window.open("./forgotPassword.html", "_self");
}


function toLogin() {
    window.open("./login.html", "_self");
}

