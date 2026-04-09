<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="mb-4">
        <h1 class="mb-3">Список резюме</h1>
    </div>

    <div class="row">
        <#list resumes as resume>
            <div class="col-md-6 col-lg-4 mb-4">
                <div class="card h-100 shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">${resume.name}</h5>
                        <p class="mb-2"><strong>Желаемая зарплата:</strong> ${resume.salary}</p>

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
</@main.layout>