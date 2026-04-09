<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="mb-4">Вход</h2>

                    <#if (RequestParameters.error??)>
                        <div class="alert alert-danger">
                            Неверный email или пароль
                        </div>
                    </#if>

                    <#if (RequestParameters.logout??)>
                        <div class="alert alert-success">
                            Вы вышли из аккаунта
                        </div>
                    </#if>

                    <form action="/login" method="post">
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="username">
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Пароль</label>
                            <input type="password" class="form-control" name="password">
                        </div>

                        <button type="submit" class="btn btn-warning w-100">Войти</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>