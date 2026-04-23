<#import "../layout.ftlh" as main>
<@main.layout>

# Мои резюме

Резюме соискателя: ${user.name} ${user.surname}

[Создать резюме](/resumes/create)

<#if resumes?size == 0>

У вас пока нет резюме

<#else>

    <#list resumes as resume>

##### ${resume.name}

Желаемая зарплата: ${resume.salary}

<#if resume.isActive?? && resume.isActive>
Активно
<#else>
Неактивно
</#if>

[Редактировать](/resumes/edit/${resume.id})

---

    </#list>

</#if>

<#if resumesPage.totalPages gt 1>
<nav>
    <#if resumesPage.hasPrevious()>
        <a href="/profile/resumes?page=${resumesPage.number - 1}&size=${resumesPage.size}">Назад</a>
    </#if>

    <#list 0..resumesPage.totalPages - 1 as i>
        <#if i == resumesPage.number>
            <strong>${i + 1}</strong>
        <#else>
            <a href="/profile/resumes?page=${i}&size=${resumesPage.size}">${i + 1}</a>
        </#if>
    </#list>

    <#if resumesPage.hasNext()>
        <a href="/profile/resumes?page=${resumesPage.number + 1}&size=${resumesPage.size}">Вперед</a>
    </#if>
</nav>
</#if>

</@main.layout>