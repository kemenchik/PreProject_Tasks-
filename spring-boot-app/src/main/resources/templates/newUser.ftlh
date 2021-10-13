<#macro newUser>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-default">New User</span>
        </div>
        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"
               id="newUserLastname" placeholder="Last Name">
        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"
               id="newUserName" placeholder="First Name">
        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"
               id="newUserLogin" placeholder="Login">
        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"
               id="newUserPassword" placeholder="Password">
    </div>
    <button id="addUserSubmit" class="btn btn-primary">
        Add user
    </button>
</#macro>