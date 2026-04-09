<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="mb-4">Создание вакансии</h2>

                    <form action="/employer/vacancies/create" method="post">
                        <div class="mb-3">
                            <label class="form-label">Название вакансии</label>
                            <input type="text" class="form-control" name="name" value="${(vacancyCreateWebDto.name)!}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Описание</label>
                            <textarea class="form-control" name="description" rows="4">${(vacancyCreateWebDto.description)!}</textarea>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Категория ID</label>
                            <input type="number" class="form-control" name="categoryId" value="${(vacancyCreateWebDto.categoryId)!}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Зарплата</label>
                            <input type="number" step="0.01" class="form-control" name="salary" value="${(vacancyCreateWebDto.salary)!}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Опыт от</label>
                            <input type="number" class="form-control" name="expFrom" value="${(vacancyCreateWebDto.expFrom)!}">
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Опыт до</label>
                            <input type="number" class="form-control" name="expTo" value="${(vacancyCreateWebDto.expTo)!}">
                        </div>

                        <button type="submit" class="btn btn-dark w-100">Создать вакансию</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>