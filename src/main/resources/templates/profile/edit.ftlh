<#import "../layout.ftlh" as main>
<#import "spring.ftl" as spring>

<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">

            <div class="card shadow-sm mb-4">
                <div class="card-body">
                    <h2 class="mb-4">Редактирование профиля</h2>

                    <form action="/profile/edit" method="post">
                        <div class="mb-3">
                            <label class="form-label">Имя</label>
                            <@spring.formInput "userEditDto.name" 'class="form-control"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Фамилия</label>
                            <@spring.formInput "userEditDto.surname" 'class="form-control"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Возраст</label>
                            <@spring.formInput "userEditDto.age" 'class="form-control" type="number"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <@spring.formInput "userEditDto.email" 'class="form-control" type="email"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Телефон</label>
                            <@spring.formInput "userEditDto.phoneNumber" 'class="form-control"' />
                            <@spring.showErrors "<br>" "text-danger" />
                        </div>

                        <button type="submit" class="btn btn-primary">Сохранить</button>
                        <a href="/profile" class="btn btn-outline-secondary">Отмена</a>
                    </form>
                </div>
            </div>

            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="mb-3">Фото профиля</h3>

                    <#if userEditDto.avatar?? && userEditDto.avatar?has_content>
                        <div class="mb-3 text-center">
                            <img src="/api/images/${userEditDto.avatar}"
                                 class="img-fluid rounded-circle border"
                                 style="max-width: 120px;">
                        </div>
                    </#if>

                    <form action="/profile/avatar" method="post" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label class="form-label">Выберите фото</label>
                            <input type="file" name="avatarFile" class="form-control" accept="image/*">
                        </div>

                        <button type="submit" class="btn btn-success">Загрузить фото</button>
                    </form>
                </div>
            </div>

        </div>
    </div>
</@main.layout>