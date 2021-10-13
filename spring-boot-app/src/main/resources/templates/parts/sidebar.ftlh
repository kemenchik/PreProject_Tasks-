<#include "security.ftlh">

<#macro sidebar>
    <#if isAdmin>
        <button class="side-bar-item btn btn-primary routerButton" id="sidebarAdmin">
            Admin
        </button>
    </#if>
    <button class="side-bar-item btn btn-light routerButton" id="sidebarUser">
        User
    </button>
</#macro>