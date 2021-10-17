<#include "security.ftlh">

<#macro header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Navbar</a>
            ${login}
            <#if authUser??>
                with roles :
                <#list authUser.getRoles() as role>
                    ${role}
                </#list>
            </#if>
        </div>
    </nav>
</#macro>