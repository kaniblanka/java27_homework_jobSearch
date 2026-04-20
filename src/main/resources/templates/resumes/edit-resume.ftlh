<#import "../layout.ftlh" as main>
<#import "/spring.ftl" as spring>

<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="mb-4">Редактирование резюме</h2>

                    <form action="/candidate/resumes/edit/${resumeId}" method="post">
                        <div class="mb-3">
                            <label class="form-label">Название резюме</label>
                            <@spring.formInput "resumeEditWebDto.name" 'class="form-control"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Категория ID</label>
                            <@spring.formInput "resumeEditWebDto.categoryId" 'class="form-control"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Желаемая зарплата</label>
                            <@spring.formInput "resumeEditWebDto.salary" 'class="form-control"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <button type="submit" class="btn btn-primary">Сохранить</button>
                        <a href="/profile/resumes" class="btn btn-outline-secondary">Отмена</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>