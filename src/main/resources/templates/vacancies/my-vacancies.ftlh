<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="d-flex justify-content-between align-items-center mb-4">
        <div>
            <h1 class="mb-2">Мои вакансии</h1>
            <p class="text-muted">Вакансии работодателя: ${user.name} ${user.surname}</p>
        </div>

        <a href="/employer/vacancies/create" class="btn btn-success">Создать вакансию</a>
    </div>

    <#if vacancies?size == 0>
        <div class="alert alert-secondary">
            У вас пока нет вакансий
        </div>
    <#else>
        <div class="row">
            <#list vacancies as vacancy>
                <div class="col-md-6 col-lg-4 mb-4">
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">${vacancy.name}</h5>
                            <p class="card-text">${vacancy.description}</p>

                            <p class="mb-1"><strong>Зарплата:</strong> ${vacancy.salary}</p>
                            <p class="mb-1"><strong>Опыт:</strong> ${vacancy.expFrom} - ${vacancy.expTo}</p>

                            <#if vacancy.isActive?? && vacancy.isActive>
                                <span class="badge bg-success">Активна</span>
                            <#else>
                                <span class="badge bg-secondary">Неактивна</span>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </#if>
</@main.layout>