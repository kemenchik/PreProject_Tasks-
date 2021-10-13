<#macro userDetails>
    <h3>User info</h3>
    <br>
    <div class="input-group mb-3">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-default">User Details</span>
        </div>
        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" id="userLastname" placeholder="Last Name">
        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" id="userName" placeholder="First Name">
        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" id="userLogin" placeholder="Login">
        <input type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default" id="userPassword" placeholder="Password">
    </div>
    <button id="putFormSubmit" class="btn btn-primary">
        Save
    </button>
</#macro>