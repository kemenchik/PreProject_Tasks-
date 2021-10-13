<#include "parts/security.ftlh">
<#import "parts/page.ftlh" as page>
<#import "adminPanel.ftlh" as admin>
<#import "userInfo.ftlh" as user>

<@page.page>
    <#if id??>
        <input type="text" style="display: none" value="${id}" id="currentUserId">
    </#if>
    <div id="admin" class="contentPage" hidden>
        <@admin.admin/>
    </div>
    <div id="user-info" class="contentPage" hidden>
        <@user.userDetails/>
    </div>
</@page.page>