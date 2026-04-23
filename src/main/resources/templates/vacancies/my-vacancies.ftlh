<#import "../layout.ftlh" as main>
<@main.layout>

# Мои вакансии

Вакансии работодателя: ${user.name} ${user.surname}

[Создать вакансию](/employer/vacancies/create)

<form method="get" class="mb-3">
    <label for="sort">Сортировка:</label>
    <select name="sort" id="sort" onchange="this.form.submit()">
        <option value="date" <#if sort == "date">selected</#if>>По дате публикации</option>
        <option value="responses" <#if sort == "responses">selected</#if>>По количеству откликов</option>
    </select>
    <input type="hidden" name="size" value="${vacanciesPage.size}">
</form>

<#if vacancies?size == 0>

У вас пока нет вакансий

<#else>

    <#list vacancies as vacancy>

#### ${vacancy.name}

${vacancy.description}

Зарплата: ${vacancy.salary}

Опыт: ${vacancy.expFrom} - ${vacancy.expTo}

<#if vacancy.isActive?? && vacancy.isActive>
Активна
<#else>
Неактивна
</#if>

[Редактировать](/employer/vacancies/edit/${vacancy.id})

---

    </#list>

</#if>

<#if vacanciesPage.totalPages gt 1>
<nav>
    <#if vacanciesPage.hasPrevious()>
        <a href="/profile/vacancies?page=${vacanciesPage.number - 1}&size=${vacanciesPage.size}&sort=${sort}">Назад</a>
    </#if>

    <#list 0..vacanciesPage.totalPages - 1 as i>
        <#if i == vacanciesPage.number>
            <strong>${i + 1}</strong>
        <#else>
            <a href="/profile/vacancies?page=${i}&size=${vacanciesPage.size}&sort=${sort}">${i + 1}</a>
        </#if>
    </#list>

    <#if vacanciesPage.hasNext()>
        <a href="/profile/vacancies?page=${vacanciesPage.number + 1}&size=${vacanciesPage.size}&sort=${sort}">Вперед</a>
    </#if>
</nav>
</#if>

</@main.layout>