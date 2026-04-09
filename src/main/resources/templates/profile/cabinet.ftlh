<#import "../layout.ftlh" as main>
<@main.layout>
    <div class="card shadow-sm">
        <div class="card-body">
            <h2 class="mb-3">Личный кабинет</h2>

            <p><strong>Имя:</strong> Имя пользователя</p>
            <p><strong>Фамилия:</strong> Фамилия пользователя</p>
            <p><strong>Email:</strong> email@example.com</p>
            <p><strong>Телефон:</strong> +996700000000</p>
            <p><strong>Тип аккаунта:</strong> CANDIDATE / EMPLOYER</p>

            <div class="mt-4">
                <a href="/profile/edit" class="btn btn-dark me-2">Редактировать профиль</a>
                <a href="/profile/vacancies" class="btn btn-outline-secondary">Мои вакансии</a>
            </div>
        </div>
    </div>
</@main.layout>