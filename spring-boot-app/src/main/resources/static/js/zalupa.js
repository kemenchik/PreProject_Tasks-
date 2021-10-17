var page = 0;

$("*").on("click", ".routerButton", (e) => {
    let els = document.getElementsByClassName("contentPage");
    Array.prototype.forEach.call(els, function (el) {
        el.hidden = true
    })
    switch (e.target.id) {
        case 'getAllUsers' :
        case 'sidebarAdmin':
            document.getElementById('admin').hidden = false;
            document.getElementById('admin-list').hidden = false;
            getAllUsers();
            break;
        case 'sidebarUser':
            document.getElementById('user-info').hidden = false;
            getUserInfoById($("#currentUserId").val());
            break;
        case 'newUser' :
            document.getElementById('admin').hidden = false;
            document.getElementById('new-user').hidden = false;
            break;
    }
})

$("*").on("click", "#putFormSubmit", () => {
    putUserInfo($("#currentUserId").val());
})

$("*").on("click", "#saveUserByIdButton", () => {
    postUserDetailsById($('#putUserId').val())
})

$("*").on("click", ".edit-button", (e) => {
    str = $(e)[0].currentTarget.id.split('-');
    id = str[str.length - 1];
    $('#exampleModalLong').modal('show');
    getPutUserById(id);
})

$("*").on("click", ".delete-button", (e) => {
    str = $(e)[0].currentTarget.id.split('-');
    id = str[str.length - 1];
    deleteUserById(id);
})

$("*").on("click", "#addUserSubmit", () => {
    createUser();
})

$("*").on("click", "#nextPage", (e) => {
    pagResultInc();
    $('#prevPage')[0].disabled = false;
})

$("*").on("click", "#prevPage", (e) => {
    pagResultDec();
})

function getAllUsers() {
    let users_list = $('#all-users-list');
    users_list.html(
        '<tr>' +
        '<td>ID</td>' +
        '<td>VK Image</td>' +
        '<td>First Name</td>' +
        '<td>Last Name</td>' +
        '<td>Login</td>' +
        '<td>Role</td>' +
        '<td>Password</td>' +
        '<td>Edit</td>' +
        '<td>Delete</td>' +
        '</tr>')
    $.ajax({
        type: "GET",
        url: 'http://localhost:1337/admin/list?page=' + page,
        success: function (result) {
            $('#nextPage')[0].disabled = result.content.length < size;
            result.content.forEach(el => {
                $.ajax({
                    type: "GET",
                    url: 'http://localhost:1337/admin/' + el.id + '/image',
                    success: function (imageUrl) {
                        var userRoles = el.roles.join(", ")
                        users_list.append(
                            '<tr>' +
                            '<td>' + el.id + '</td>' +
                            '<td>' +
                            '<img src="' + imageUrl + '">'
                            + '</td>' +
                            '<td>' + el.name + '</td>' +
                            '<td>' + el.lastName + '</td>' +
                            '<td>' + el.login + '</td>' +
                            '<td>' + userRoles + '</td>' +
                            '<td>' + el.password + '</td>' +
                            '<td>' +
                            '<button type="button" class="btn btn-primary edit-button" data-toggle="modal" data-target="#exampleModal" id="' + 'user-put-id-' + el.id + '">Edit</button>' +
                            '</td>' +
                            '<td>' +
                            '<button type="button" class="btn btn-warning delete-button" id="' + 'user-delete-id-' + el.id + '">Delete</button>' +
                            '</td>' +
                            '</tr>'
                        )
                    }
                })
            })
        }
    })
    $('#prevPage')[0].disabled = page < 1;
}

function pagResultInc() {
    page++;
    getAllUsers();
}

function pagResultDec() {
    page--;
    getAllUsers();
}

function getUserInfoById(id) {
    $.ajax({
        type: "GET",
        url: 'http://localhost:1337/admin/' + id,
        success: function (result) {
            $('#userLastname').val(result.lastName);
            $('#userName').val(result.name);
            $('#userLogin').val(result.login);
            $('#userPassword').val(result.password);
        }
    })
    $.ajax({
        type: "GET",
        url: 'http://localhost:1337/admin/' + id + '/image',
        success: function (result) {
            $('#userImage').attr('src', result);
        }
    })
}

function putUserInfo(id) {
    $.ajax({
        type: "POST",
        data: {
            lastname: $('#userLastname').val(),
            name: $('#userName').val(),
            login: $('#userLogin').val(),
            password: $('#userPassword').val(),
            id: id
        },
        url: 'http://localhost:1337/admin/put/' + id,
    })
}

function createUser() {
    $.ajax({
        type: "POST",
        data: {
            lastname: $('#newUserLastname').val(),
            name: $('#newUserName').val(),
            login: $('#newUserLogin').val(),
            password: $('#newUserPassword').val(),
            vkUserId: $('#newUserVkId').val()
        },
        url: 'http://localhost:1337/admin/create',
    })
}

function getPutUserById(id) {
    $.ajax({
        type: "GET",
        url: 'http://localhost:1337/admin/' + id,
        success: function (result) {
            $('#putUserId').val(result.id);
            $('#putUserLastname').val(result.lastName);
            $('#putUserName').val(result.name);
            $('#putUserLogin').val(result.login);
            $('#putUserPassword').val(result.password);
            `            $('#putUserIsAdmin')[0].checked = result.admin;
`
        }
    }).done(() => {
        getAllUsers();
    })
}

function postUserDetailsById(id) {
    $.ajax({
        type: "POST",
        data: {
            id: id,
            lastname: $('#putUserLastname').val(),
            name: $('#putUserName').val(),
            login: $('#putUserLogin').val(),
            password: $('#putUserPassword').val(),
            isAdmin: $('#putUserIsAdmin')[0].checked
        },
        url: 'http://localhost:1337/admin/put/' + id,
    }).done(() => {
        getAllUsers();
    })
}

function deleteUserById(id) {
    $.ajax({
        type: "POST",
        url: 'http://localhost:1337/admin/delete/' + id
    }).done(() => {
        getAllUsers();
    })
}
