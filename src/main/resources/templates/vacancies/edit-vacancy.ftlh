<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="mb-4">Редактирование вакансии</h2>

                    <#if expError??>
                        <div class="alert alert-danger">
                            ${expError}
                        </div>
                    </#if>

                    <form action="/employer/vacancies/edit/${vacancyId}" method="post">
                        <div class="mb-3">
                            <label class="form-label">Название вакансии</label>
                            <@spring.formInput "vacancyCreateWebDto.name" 'class="form-control"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Описание</label>
                            <@spring.formTextarea "vacancyCreateWebDto.description" 'class="form-control" rows="4"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Категория ID</label>
                            <@spring.formInput "vacancyCreateWebDto.categoryId" 'class="form-control"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Зарплата</label>
                            <@spring.formInput "vacancyCreateWebDto.salary" 'class="form-control"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Опыт от</label>
                            <@spring.formInput "vacancyCreateWebDto.expFrom" 'class="form-control"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Опыт до</label>
                            <@spring.formInput "vacancyCreateWebDto.expTo" 'class="form-control"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <button type="submit" class="btn btn-primary">Сохранить</button>
                        <a href="/profile/vacancies" class="btn btn-outline-secondary">Отмена</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>