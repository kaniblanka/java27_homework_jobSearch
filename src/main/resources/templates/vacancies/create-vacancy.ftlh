<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="mb-4">Создание вакансии</h2>

                    <#if expError??>
                        <div class="alert alert-danger">
                            ${expError}
                        </div>
                    </#if>

                    <#if springMacroRequestContext?? && springMacroRequestContext.errors?has_content>
                        <div class="alert alert-danger">
                            Проверь заполнение формы
                        </div>
                    </#if>

                    <form action="/employer/vacancies/create" method="post">
                        <div class="mb-3">
                            <label class="form-label">Название вакансии</label>
                            <input type="text"
                                   class="form-control"
                                   name="name"
                                   placeholder="Например: Backend разработчик"
                                   value="${(vacancyCreateWebDto.name)!}">

                            <#if springMacroRequestContext?? && springMacroRequestContext.getErrors("vacancyCreateWebDto.name")??>
                                <div class="text-danger small mt-1">
                                    ${springMacroRequestContext.getErrors("vacancyCreateWebDto.name").getFieldError().defaultMessage}
                                </div>
                            </#if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Описание</label>
                            <textarea class="form-control"
                                      name="description"
                                      rows="4"
                                      placeholder="Опишите обязанности и требования">${(vacancyCreateWebDto.description)!}</textarea>

                            <#if springMacroRequestContext?? && springMacroRequestContext.getErrors("vacancyCreateWebDto.description")??>
                                <div class="text-danger small mt-1">
                                    ${springMacroRequestContext.getErrors("vacancyCreateWebDto.description").getFieldError().defaultMessage}
                                </div>
                            </#if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Категория ID</label>
                            <input type="number"
                                   class="form-control"
                                   name="categoryId"
                                   min="1"
                                   step="1"
                                   oninput="this.value = this.value.replace('-', '')"
                                   value="${(vacancyCreateWebDto.categoryId)!}">

                            <#if springMacroRequestContext?? && springMacroRequestContext.getErrors("vacancyCreateWebDto.categoryId")??>
                                <div class="text-danger small mt-1">
                                    ${springMacroRequestContext.getErrors("vacancyCreateWebDto.categoryId").getFieldError().defaultMessage}
                                </div>
                            </#if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Зарплата</label>
                            <input type="number"
                                   step="0.01"
                                   min="0"
                                   class="form-control"
                                   name="salary"
                                   oninput="this.value = this.value.replace('-', '')"
                                   value="${(vacancyCreateWebDto.salary)!}">

                            <#if springMacroRequestContext?? && springMacroRequestContext.getErrors("vacancyCreateWebDto.salary")??>
                                <div class="text-danger small mt-1">
                                    ${springMacroRequestContext.getErrors("vacancyCreateWebDto.salary").getFieldError().defaultMessage}
                                </div>
                            </#if>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Опыт от</label>
                            <input type="number"
                                   class="form-control"
                                   name="expFrom"
                                   min="0"
                                   max="50"
                                   step="1"
                                   oninput="this.value = this.value.replace('-', '')"
                                   value="${(vacancyCreateWebDto.expFrom)!}">

                            <#if springMacroRequestContext?? && springMacroRequestContext.getErrors("vacancyCreateWebDto.expFrom")??>
                                <div class="text-danger small mt-1">
                                    ${springMacroRequestContext.getErrors("vacancyCreateWebDto.expFrom").getFieldError().defaultMessage}
                                </div>
                            </#if>
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Опыт до</label>
                            <input type="number"
                                   class="form-control"
                                   name="expTo"
                                   min="0"
                                   max="50"
                                   step="1"
                                   oninput="this.value = this.value.replace('-', '')"
                                   value="${(vacancyCreateWebDto.expTo)!}">

                            <#if springMacroRequestContext?? && springMacroRequestContext.getErrors("vacancyCreateWebDto.expTo")??>
                                <div class="text-danger small mt-1">
                                    ${springMacroRequestContext.getErrors("vacancyCreateWebDto.expTo").getFieldError().defaultMessage}
                                </div>
                            </#if>
                        </div>

                        <button type="submit" class="btn btn-dark w-100">Создать вакансию</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>