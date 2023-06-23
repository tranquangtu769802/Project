var listAccount = [];
var listDepartment = [];
var editTime = -1;

//khai bao bien trong login bên login.js
var acc = JSON.parse(localStorage.getItem("accLogin"));
var userLogin = acc.userName;
var passwordLogin = acc.password;

// khai bao bien dung trong page
var totalPage = 0; // tong so trang
var page = 0;  // trang
var size = 5; // so phan tu trong 1 trang, phai trung vs gia tri size ban dau dat o #inputSize

// khai báo biến sort
var sortField = "";
var isAsc = true // true: sắp xếp theo kiểu asc, false: sắp xếp theo kiểu Desc

// khai báo biến search
v_search = "";


$(function () {
    getAccount();
    getDepartment();
});

function getAccount() {
    v_url = "";

    // // sort du lieu
    if (isAsc) {
        v_url = `http://localhost:8075/api/v1/accounts?page=${page}&size=${size}&sort=${sortField},asc`
    } else {
        v_url = `http://localhost:8075/api/v1/accounts?page=${page}&size=${size}&sort=${sortField},desc`
    }
    v_url = v_url + "&search=" + v_search;
    // http://localhost:8075/api/v1/accounts?page=${page}&size=${size}&sort=${sortField}, isAsc

    //jqajax
    $.ajax({
        type: "GET",
        url: v_url,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(`${userLogin}:${passwordLogin}`));
        },
        dataType: "JSON",
        success: function (response) {
            listAccount = [];
            listAccount = response.content; // do Account dung page nen  them .content
            totalPage = response.totalPages;
            $("#tbodyAccount").empty();  // xoa trang bang truoc khi hien thi
            for (let i = 0; i < listAccount.length; i++) {  // dung vong for de hien thi danh sach account
                //dung append de in noi vao trong tablebody, cac bien se an theo cac bien trong java, thu tu dien cac bien cung phai trung voi ben html
                $("#tbodyAccount").append(`
                <tr>
                        <td>${listAccount[i].id}</td>
                        <td>${listAccount[i].userName}</td>
                        <td>${listAccount[i].firstName}</td>
                        <td>${listAccount[i].lastName}</td>
                        <td>${listAccount[i].role}</td>
                        <td>${listAccount[i].departmentName}</td>
                        <td>${listAccount[i].email}</td>

                        <td>
                            <button type="button" class="btn btn-success" onclick="deleteAccountById(${listAccount[i].id})">DELETE</button>   
                        </td>

                        <td>
                            <button type="button" class="btn btn-success" onclick="getAccountById(${listAccount[i].id})">EDIT</button>   
                        </td>

                </tr>              
                `);

            }
            showPage();
        },
        error: function (response) {
            alert("call api khong thanh cong");

        }
    });
}


function deleteAccountById(id) {
    if (editTime != -1) {
        alert("Không thể thực hiện lệnh khi đang edit")
        return;
    } else {
        var isDelete = confirm("Bạn có chắc muốn xóa không ?");
        if (isDelete) {
            $.ajax({
                type: "DELETE",
                url: "http://localhost:8075/api/v1/accounts/" + id,
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Basic " + btoa(`${userLogin}:${passwordLogin}`));
                },
                success: function (response) {
                    alert("Xóa thành công, danh sách đã được cập nhật lại!");
                    getAccount();
                    return;
                },
                error: function (response) {
                    confirm("Đã xảy ra lỗi khi thực hiện! Xin hãy thực hiện lại!");
                    getAccount();
                    return;
                }
            });

        }
    }

}

function getDepartment() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8075/api/v1/departments",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(`${userLogin}:${passwordLogin}`));
        },
        dataType: "JSON",
        success: function (response) {
            listDepartment = [];
            listDepartment = response; // sửa lại phần adv về dạng list vì bs3-select:h không chấp nhận dạng page
            $("#inputDepartment").empty(); //làm trống phần hiển thị trước khi nhập thông tin
            for (let i = 0; i < listDepartment.length; i++) {
                $("#inputDepartment").append(`
                <option value="${listDepartment[i].id}">${listDepartment[i].name}</option>
                `)
            }
        },
        error: function (response) {
            alert("call api thất bại!");
        }
    });
}

function checkNull() {
    var check_email = $("#inputEmail").val();
    var check_firstName = $("#inputFirstName").val();
    var check_lastName = $("#inputLastName").val();
    var check_userName = $("#inputUserName").val();

    if (check_email == "" || check_firstName == "" || check_lastName == "" || check_userName == "") {
        return false;
    }
    return true;
}

function addAccount() {
    if (editTime != -1) {
        alert("Đang edit, không thể thêm danh sách")
        return;
    } else {
        if (checkNull() == false) {
            alert("Vui lòng không để trống dữ liệu")
        } else {
            //lấy giá trị từ html
            var v_email = $("#inputEmail").val();
            var v_firstName = $("#inputFirstName").val();
            var v_lastName = $("#inputLastName").val();
            var v_userName = $("#inputUserName").val();
            var v_role = $("#inputRole").val();
            var v_department = $("#inputDepartment").val();

            var newAccount = {
                email: v_email,
                firstName: v_firstName,
                lastName: v_lastName,
                userName: v_userName,
                role: v_role,
                departmentId: v_department,
                password: "123456",
            };

            $.ajax({
                type: "POST",
                url: "http://localhost:8075/api/v1/accounts",
                data: JSON.stringify(newAccount),  // chuyen json ve dang backend doc dc
                contentType: "application/json; charset=UTF-8",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Authorization", "Basic " + btoa(`${userLogin}:${passwordLogin}`));
                },
                success: function (response) {
                    alert("them thanh cong");

                    $("#inputEmail").val("");
                    $("#inputFirstName").val("");
                    $("#inputLastName").val("");
                    $("#inputUserName").val("");
                    $("#inputRole").val("");
                    $("#inputDepartment").val("");
                    getAccount();
                },
                error: function (response) {
                    alert("them that bai");
                },

            });
        }
    }
}


function getAccountById(idParam) {
    // goi toi backend de lay thong tin account can sua(edit), thực hiện event onclick Edit
    $.ajax({
        type: "GET",
        url: "http://localhost:8075/api/v1/accounts/" + idParam,
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(`${userLogin}:${passwordLogin}`));
        },
        dataType: "JSON",
        success: function (response) {
            $("#inputEmail").val(response.email);
            $("#inputFirstName").val(response.firstName);
            $("#inputLastName").val(response.lastName);
            $("#inputUserName").val(response.userName);
            $("#inputRole").val(response.role);

            // for (let i = 0; i < listDepartment.lengtht; i++) {
            //     if (listDepartment[i].name == response.departmentName) {
            //         $("#inutDepartment").val(listDepartment[i].id);
            //     }
            // }

            var depId = listDepartment.find((dep) => dep.name == response.departmentName).id;
            $("#inputDepartment").val(depId);
            editTime = idParam; //gán gtri của idParam cho editTime để xác định index trỏ tới
            // không được gán idParam = editTime vì giá trị editTime ban đầu = -1 =>giá trị idParam luôn bằng -1

        },

        error: function (response) {
            alert("lay thong account khong thanh cong")
        }
    });
}

function updateAccount() {
    if (editTime == -1) {
        alert("chon account truoc khi update");
        return;
    }

    //lấy giá trị từ html
    var v_email = $("#inputEmail").val();
    var v_firstName = $("#inputFirstName").val();
    var v_lastName = $("#inputLastName").val();
    var v_userName = $("#inputUserName").val();
    var v_role = $("#inputRole").val();
    var v_department = $("#inputDepartment").val();

    //thêm giá trị mới bên account
    var new_Account = {
        email: v_email,
        firstName: v_firstName,
        lastName: v_lastName,
        role: v_role,
        userName: v_userName,
        departmentId: v_department,
        password: "123456",
    };

    // update thong tin, event onclick update
    $.ajax({
        type: "PUT",
        url: "http://localhost:8075/api/v1/accounts/" + editTime,//khi an edit => tự động dóng tới id => phần tử index tương ứng (index >=0)
        data: JSON.stringify(new_Account),  // chuyen json ve dang backend doc dc
        contentType: "application/json; charset=UTF-8",
        beforeSend: function (xhr) {
            xhr.setRequestHeader("Authorization", "Basic " + btoa(`${userLogin}:${passwordLogin}`));
        },
        success: function (response) {
            alert("Update thanh cong");
            editTime = -1;  // set edit ve trang thai ban dau
            $("#inputEmail").val("");
            $("#inputFirstName").val("");
            $("#inputLastName").val("");
            $("#inputUserName").val("");
            $("#inputRole").val("");
            $("#inputDepartment").val("");
            getAccount();
        },
        error: function (response) {
            alert("update khong thanh cong");
        }
    });

}

function showPage() {
    $("#pageId").empty();
    $("#pageId").append(`<li><a href="#" onclick="changeFirst()">&laquo;</a></li>`);

    for (let i = 1; i <= Number.parseInt(totalPage); i++) {
        if ((i - 1) == page) {
            $("#pageId").append(`<li><a href="#" onclick="changePage(${i - 1})" class = "active">${i}</a></li>`) //dùng active để biết trang đang trỏ tới
        } else {
            $("#pageId").append(`<li><a href="#" onclick="changePage(${i - 1})">${i}</a></li>`)
        }
    }

    $("#pageId").append(`<li><a href="#" onclick="changeLast()">&raquo;</a></li>`);
}

function changeFirst() {
    page = 0;
    getAccount();
}
function changePage(i) {
    // lệnh if để không load lại dữ liệu khi ấn cùng 1 page nhiều lần liên tục
    if (i == page)
        return;
    page = i;
    getAccount();
}
function changeLast() {
    page = totalPage - 1;
    getAccount();
}

// function showPage() {
//     // alert(totalPage);
//     $("#pageId").empty();
//     $("#pageId").append(`<li><button type="button" onclick="changeFirst()" class="btn btn-default">&laquo;</button></li>`);

//     for (let i = 1; i <= Number.parseInt(totalPage); i++) {
//         if ((i - 1) == page) {
//             $("#pageId").append(`
//                 <li><button type="button" onclick="changePage(${i - 1})" class="btn btn-default active" >${i}</button></li>
//             `) // active hien thi dam so trang
//         } else {
//             $("#pageId").append(`
//                 <li><button type="button" onclick="changePage(${i - 1})" class="btn btn-default">${i}</button></li>
//             `)
//         }
//     }

//     $("#pageId").append(` <li><button type="button" onclick="changeLast()" class="btn btn-default">&raquo;</button></li>`);
// }

// function changeFirst() {
//     page = 0;
//     getAccount();
// }

// function changeLast() {
//     page = totalPage - 1;
//     getAccount();
// }

// function changePage(i) {
//     // tranh get nhieu lan
//     if (i == page)
//         return;
//     page = i;
//     getAccount();
// }

function changeSize(e) {
    // lay so phan tu muon hien thi trong 1 trang
    size = e.value;
    page = 0;
    getAccount();
};

// sortFieldParam === sortField: neu cot sort trung vs cot sort hien tai(===), doi chieu chieu sort
// sortField = sortFieldParam: neu cot sort khac vs cot sort hien tai(=), mac dinh asc
function handleSort(sortFieldParam) {

    if (sortFieldParam === sortField) {
        isAsc = !isAsc
    } else {
        sortField = sortFieldParam;
        isAsc = true;
    }
    getAccount();

}


// hàm search
function handleSearch() {
    v_dataSearch = $("#inputSearch").val();
    v_search = v_dataSearch;
    getAccount();
}
