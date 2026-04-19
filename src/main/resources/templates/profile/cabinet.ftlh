<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="mb-4">Личный кабинет</h2>

            <div class="row">
                <div class="col-md-3 text-center mb-3">
                    <#if user.avatar?? && user.avatar?has_content>
                        <img src="/api/images/${user.avatar}" alt="avatar" class="img-fluid rounded-circle border"
                             style="max-width: 180px;">
                    <#else>
                        <div class="border rounded-circle d-flex align-items-center justify-content-center mx-auto"
                             style="width: 180px; height: 180px;">
                            No Avatar
                        </div>
                    </#if>
                </div>

                <div class="col-md-9">
                    <p><strong>Имя:</strong> ${user.name}</p>
                    <p><strong>Фамилия:</strong> ${user.surname}</p>
                    <p><strong>Возраст:</strong> ${user.age}</p>
                    <p><strong>Email:</strong> ${user.email}</p>
                    <p><strong>Телефон:</strong> ${user.phoneNumber}</p>
                    <p><strong>Тип аккаунта:</strong> ${user.accountType}</p>

                    <div class="mt-4">
                        <a href="/profile/edit" class="btn btn-dark me-2">Редактировать профиль</a>

                        <#if user.accountType == "EMPLOYER">
                            <a href="/profile/vacancies" class="btn btn-outline-secondary me-2">Мои вакансии</a>
                            <a href="/employer/resumes" class="btn btn-outline-primary me-2">Смотреть резюме</a>
                            <a href="/employer/vacancies/create" class="btn btn-success">Создать вакансию</a>
                        </#if>

                        <#if user.accountType == "CANDIDATE">
                            <a href="/profile/resumes" class="btn btn-outline-secondary me-2">Мои резюме</a>
                            <a href="/candidate/resumes/create" class="btn btn-success">Создать резюме</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</@main.layout>