package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    public User createUser(User user) {
        // тут регистрируем пользователя
        // приходит объект user с фронта
        // потом он должен сохраниться
        return user;
    }

    public Optional<User> getJobSeekerById(Long id) {
        // ищем соискателя по id
        // если нашли возвращаем, если нет то empty
        return Optional.empty();
    }

    public Optional<User> getEmployerById(Long id) {
        // ищем работодателя по id
        // логика такая же как и сверху
        return Optional.empty();
    }

    public Optional<User> login(String email, String password) {
        // тут должна быть авторизация
        // проверяем email и пароль
        // если все ок, возвращаем пользователя
        // если нет, пусто
        return Optional.empty();
    }
}