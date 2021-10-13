<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    authUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    login = authUser.getLogin()
    id = authUser.getId()
    isAdmin = authUser.isAdmin()
    >
<#else>
    <#assign
    login = 'unAuth'
    isAdmin = false
    >
</#if>