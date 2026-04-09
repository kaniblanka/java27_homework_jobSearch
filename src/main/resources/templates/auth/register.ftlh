<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h2 class="mb-4">Регистрация</h2>

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
                            <label class="form-label">Пароль</label>
                            <input type="password" class="form-control" name="password">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Телефон</label>
                            <input type="text" class="form-control" name="phoneNumber">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Аватар</label>
                            <input type="text" class="form-control" name="avatar" placeholder="Ссылка на изображение">
                        </div>

                        <div class="mb-4">
                            <label class="form-label">Тип аккаунта</label>
                            <select class="form-select" name="accountType">
                                <option value="CANDIDATE">Соискатель</option>
                                <option value="EMPLOYER">Работодатель</option>
                            </select>
                        </div>

                        <button type="submit" class="btn btn-dark w-100">Зарегистрироваться</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@main.layout>