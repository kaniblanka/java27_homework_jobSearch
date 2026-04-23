<#import "../layout.ftlh" as main>
<@main.layout>

# Все резюме

<#if resumes?size == 0>

Пока нет доступных резюме

<#else>

    <#list resumes as resume>

##### ${resume.name}

Соискатель: ${resume.applicantName}

Категория: ${resume.categoryName}

Желаемая зарплата: ${resume.salary}

<#if resume.isActive?? && resume.isActive>
Активно
<#else>
Неактивно
</#if>

---

    </#list>

</#if>

<#if resumesPage.totalPages gt 1>
<nav>
    <#if resumesPage.hasPrevious()>
        <a href="/resumes?page=${resumesPage.number - 1}&size=${resumesPage.size}">Назад</a>
    </#if>

    <#list 0..resumesPage.totalPages - 1 as i>
        <#if i == resumesPage.number>
            <strong>${i + 1}</strong>
        <#else>
            <a href="/resumes?page=${i}&size=${resumesPage.size}">${i + 1}</a>
        </#if>
    </#list>

    <#if resumesPage.hasNext()>
        <a href="/resumes?page=${resumesPage.number + 1}&size=${resumesPage.size}">Вперед</a>
    </#if>
</nav>
</#if>

</@main.layout>