<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="mb-4">Редактирование профиля</h2>

                    <form>
                        <div class="mb-3">
                            <label class="form-label">Имя</label>
                            <input type="text" class="form-control" name="name">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Фамилия</label>
                            <input type="text" class="form-control" name="surname">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Возраст</label>
                            <input type="number" class="form-control" name="age">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Телефон</label>
                            <input type="text" class="form-control" name="phoneNumber">
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Аватар</label>
                            <input type="text" class="form-control" name="avatar">
                        </div>

                        <button type="submit" class="btn btn-dark w-100">Сохранить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>