<#import "newUser.ftlh" as nuser>

<#macro admin>
    <h3>
        Admin panel
    </h3>
    <br>
    <div style="display: flex; flex-direction: row">
        <button type="button" class="btn btn-light routerButton" id="getAllUsers" style="margin-right: 1rem;">Users
            table
        </button>
        <button type="button" class="btn btn-light routerButton" id="newUser">New user</button>
    </div>
    <br>
    <div id="admin-list" class="contentPage">
        <table class="table table-striped" id="all-users-list">
            <tr>
                <td>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Edit
                    </button>
                </td>
                <td>
                    <button type="button" class="btn btn-warning">Delete</button>
                </td>
            </tr>
        </table>
        <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Modal title</h5>
                    </div>
                    <div class="modal-body">
                        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"
                               id="putUserId" disabled>
                        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"
                               id="putUserLastname" placeholder="Last Name">
                        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"
                               id="putUserName" placeholder="First Name">
                        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"
                               id="putUserLogin" placeholder="Login">
                        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"
                               id="putUserPassword" placeholder="Password">
                        <input type="checkbox" id="putUserIsAdmin"> Is admin
                    </div>
                    <button class=" btn btn-primary" id="saveUserByIdButton">Save</button>
                </div>
            </div>
        </div>
    </div>


    <div id="new-user" class="contentPage">
        <@nuser.newUser/>
    </div>
</#macro>