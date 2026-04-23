<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="container mt-4">
        <h2 class="mb-3">Мои резюме</h2>
        <p class="text-muted">Резюме соискателя: ${user.name} ${user.surname}</p>

        <div class="mb-3">
            <a href="/resumes/create" class="btn btn-primary">Создать резюме</a>
        </div>

        <#if resumes?size == 0>
            <div class="alert alert-info">У вас пока нет резюме</div>
        <#else>
            <#list resumes as resume>
                <div class="card mb-3 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">${resume.name}</h5>

                        <p class="mb-1">
                            <strong>Категория:</strong>
                            ${resume.categoryName!""}
                        </p>

                        <p class="mb-1">
                            <strong>Желаемая зарплата:</strong>
                            ${resume.salary!""}
                        </p>

                        <p class="mb-3">
                            <strong>Статус:</strong>
                            <#if resume.isActive?? && resume.isActive>
                                Активно
                            <#else>
                                Неактивно
                            </#if>
                        </p>

                        <a href="/resumes/edit/${resume.id}" class="btn btn-outline-primary btn-sm">
                            Редактировать
                        </a>
                    </div>
                </div>
            </#list>
        </#if>

        <#if resumesPage.totalPages gt 1>
            <nav class="mt-4">
                <ul class="pagination">
                    <#if resumesPage.hasPrevious()>
                        <li class="page-item">
                            <a class="page-link"
                               href="/profile/resumes?page=${resumesPage.number - 1}&size=${resumesPage.size}">
                                Назад
                            </a>
                        </li>
                    </#if>

                    <#list 0..resumesPage.totalPages - 1 as i>
                        <li class="page-item <#if i == resumesPage.number>active</#if>">
                            <a class="page-link"
                               href="/profile/resumes?page=${i}&size=${resumesPage.size}">
                                ${i + 1}
                            </a>
                        </li>
                    </#list>

                    <#if resumesPage.hasNext()>
                        <li class="page-item">
                            <a class="page-link"
                               href="/profile/resumes?page=${resumesPage.number + 1}&size=${resumesPage.size}">
                                Вперед
                            </a>
                        </li>
                    </#if>
                </ul>
            </nav>
        </#if>
    </div>
</@main.layout>