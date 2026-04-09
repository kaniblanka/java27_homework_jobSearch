<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="mb-4">Создание резюме</h2>

                    <form action="/candidate/resumes/create" method="post">
                        <div class="mb-3">
                            <label class="form-label">Название резюме</label>
                            <input type="text"
                                   class="form-control"
                                   name="name"
                                   value="${(resumeCreateWebDto.name)!}">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Категория ID</label>
                            <input type="number"
                                   class="form-control"
                                   name="categoryId"
                                   value="${(resumeCreateWebDto.categoryId)!}">
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Желаемая зарплата</label>
                            <input type="number"
                                   step="0.01"
                                   class="form-control"
                                   name="salary"
                                   value="${(resumeCreateWebDto.salary)!}">
                        </div>

                        <button type="submit" class="btn btn-dark w-100">Создать резюме</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>