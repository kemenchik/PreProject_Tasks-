<#import "header.ftlh" as header>
<#import "sidebar.ftlh" as sidebar>
<#macro page>
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
        <script type="text/javascript" src="/static/js/zalupa.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
                crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
              crossorigin="anonymous">
        <link rel="stylesheet" href="/static/css/style.css">
        <title>Title</title>
    </head>
    <body>
    <div class="header">
        <@header.header/>
    </div>
    <div class="grid-container">
        <div class="side-bar">
            <@sidebar.sidebar/>
        </div>
        <div class="content">
            <#nested>
        </div>
    </div>
    </body>
    </html>
</#macro>