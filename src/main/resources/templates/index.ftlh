<#import "layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-3">Все вакансии</h2>
        <p class="text-muted">Главная страница сайта со списком активных вакансий</p>

        <form method="get" class="mb-4">
            <div class="row g-2 align-items-center">
                <div class="col-auto">
                    <label for="sort" class="col-form-label">Сортировка:</label>
                </div>
                <div class="col-auto">
                    <select name="sort" id="sort" class="form-select" onchange="this.form.submit()">
                        <option value="date" <#if sort == "date">selected</#if>>По дате публикации</option>
                        <option value="responses" <#if sort == "responses">selected</#if>>По количеству откликов</option>
                    </select>
                </div>
                <input type="hidden" name="size" value="${vacanciesPage.size}">
            </div>
        </form>

        <#if vacancies?size == 0>
            <div class="alert alert-info">На данный момент активных вакансий нет</div>
        <#else>
            <#list vacancies as vacancy>
                <div class="card mb-3 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">${vacancy.name}</h5>
                        <p class="card-text">${vacancy.description}</p>
                        <p class="mb-1"><strong>Зарплата:</strong> ${vacancy.salary!""}</p>
                        <p class="mb-1"><strong>Опыт:</strong> ${vacancy.expFrom!""} - ${vacancy.expTo!""} лет</p>
                        <p class="mb-0">
                            <strong>Статус:</strong>
                            <#if vacancy.isActive?? && vacancy.isActive>
                                Активна
                            <#else>
                                Неактивна
                            </#if>
                        </p>
                    </div>
                </div>
            </#list>
        </#if>

        <#if vacanciesPage.totalPages gt 1>
            <nav class="mt-4">
                <ul class="pagination">
                    <#if vacanciesPage.hasPrevious()>
                        <li class="page-item">
                            <a class="page-link" href="/?page=${vacanciesPage.number - 1}&size=${vacanciesPage.size}&sort=${sort}">Назад</a>
                        </li>
                    </#if>

                    <#list 0..vacanciesPage.totalPages - 1 as i>
                        <li class="page-item <#if i == vacanciesPage.number>active</#if>">
                            <a class="page-link" href="/?page=${i}&size=${vacanciesPage.size}&sort=${sort}">${i + 1}</a>
                        </li>
                    </#list>

                    <#if vacanciesPage.hasNext()>
                        <li class="page-item">
                            <a class="page-link" href="/?page=${vacanciesPage.number + 1}&size=${vacanciesPage.size}&sort=${sort}">Вперед</a>
                        </li>
                    </#if>
                </ul>
            </nav>
        </#if>
    </div>
</@main.layout>