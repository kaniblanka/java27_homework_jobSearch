<#macro layout>
    <!doctype html>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
              rel="stylesheet"
              crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
                crossorigin="anonymous"></script>

        <title>JobSearch</title>
    </head>
    <body class="bg-light">

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="/">JobSearch</a>

            <button class="navbar-toggler"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#navbarNav"
                    aria-controls="navbarNav"
                    aria-expanded="false"
                    aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">

                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/vacancies">Вакансии</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/resumes">Резюме</a>
                    </li>

                    <#if isAuthenticated>
                        <li class="nav-item">
                            <a class="nav-link" href="/profile">Личный кабинет</a>
                        </li>
                    </#if>
                </ul>

                <div class="d-flex">
                    <#if isAuthenticated>
                        <form action="/logout" method="post" class="d-inline">
                            <button type="submit" class="btn btn-danger">Выйти</button>
                        </form>
                    <#else>
                        <a href="/register" class="btn btn-outline-light me-2">Регистрация</a>
                        <a href="/login" class="btn btn-warning">Войти</a>
                    </#if>
                </div>

            </div>
        </div>
    </nav>

    <div class="container py-4">
        <#nested>
    </div>

    </body>
    </html>
</#macro>