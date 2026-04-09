<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="mb-4">
        <h1 class="mb-3">Все вакансии</h1>
    </div>

    <div class="row">
        <#list vacancies as vacancy>
            <div class="col-md-6 col-lg-4 mb-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">${vacancy.name}</h5>
                        <p class="card-text">${vacancy.description}</p>
                        <p><strong>Зарплата:</strong> ${vacancy.salary}</p>
                        <p><strong>Опыт:</strong> ${vacancy.expFrom} - ${vacancy.expTo}</p>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</@main.layout>