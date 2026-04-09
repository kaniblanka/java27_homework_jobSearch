<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="mb-4">Создание резюме</h2>

                    <#if springMacroRequestContext?? && springMacroRequestContext.errors?has_content>
                        <div class="alert alert-danger">
                            Проверь заполнение формы
                        </div>
                    </#if>

                    <form action="/candidate/resumes/create" method="post">

                        <!-- Название -->
                        <div class="mb-3">
                            <label class="form-label">Название резюме</label>
                            <input type="text"
                                   class="form-control"
                                   name="name"
                                   placeholder="Например: Java разработчик"
                                   value="${(resumeCreateWebDto.name)!}">

                            <#if springMacroRequestContext?? && springMacroRequestContext.getErrors("resumeCreateWebDto.name")??>
                                <div class="text-danger small mt-1">
                                    ${springMacroRequestContext.getErrors("resumeCreateWebDto.name").getFieldError().defaultMessage}
                                </div>
                            </#if>
                        </div>

                        <!-- Категория -->
                        <div class="mb-3">
                            <label class="form-label">Категория ID</label>
                            <input type="number"
                                   class="form-control"
                                   name="categoryId"
                                   min="1"
                                   step="1"
                                   oninput="this.value = this.value.replace('-', '')"
                                   value="${(resumeCreateWebDto.categoryId)!}">

                            <#if springMacroRequestContext?? && springMacroRequestContext.getErrors("resumeCreateWebDto.categoryId")??>
                                <div class="text-danger small mt-1">
                                    ${springMacroRequestContext.getErrors("resumeCreateWebDto.categoryId").getFieldError().defaultMessage}
                                </div>
                            </#if>
                        </div>

                        <!-- Зарплата -->
                        <div class="mb-4">
                            <label class="form-label">Ожидаемая зарплата</label>
                            <input type="number"
                                   step="0.01"
                                   min="0"
                                   class="form-control"
                                   name="salary"
                                   oninput="this.value = this.value.replace('-', '')"
                                   value="${(resumeCreateWebDto.salary)!}">

                            <#if springMacroRequestContext?? && springMacroRequestContext.getErrors("resumeCreateWebDto.salary")??>
                                <div class="text-danger small mt-1">
                                    ${springMacroRequestContext.getErrors("resumeCreateWebDto.salary").getFieldError().defaultMessage}
                                </div>
                            </#if>
                        </div>

                        <!-- Кнопка -->
                        <button type="submit" class="btn btn-dark w-100">
                            Создать резюме
                        </button>

                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>