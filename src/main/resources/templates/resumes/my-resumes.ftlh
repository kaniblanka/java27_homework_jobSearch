<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="d-flex justify-content-between align-items-center mb-4">
        <div>
            <h1 class="mb-2">Мои резюме</h1>
            <p class="text-muted">Резюме соискателя: ${user.name} ${user.surname}</p>
        </div>

        <a href="/candidate/resumes/create" class="btn btn-success">Создать резюме</a>
    </div>

    <#if resumes?size == 0>
        <div class="alert alert-secondary">
            У вас пока нет резюме
        </div>
    <#else>
        <div class="row">
            <#list resumes as resume>
                <div class="col-md-6 col-lg-4 mb-4">
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">${resume.name}</h5>

                            <p class="mb-2">
                                <strong>Желаемая зарплата:</strong> ${resume.salary}
                            </p>

                            <#if resume.isActive?? && resume.isActive>
                                <span class="badge bg-success">Активно</span>
                            <#else>
                                <span class="badge bg-secondary">Неактивно</span>
                            </#if>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </#if>
</@main.layout>