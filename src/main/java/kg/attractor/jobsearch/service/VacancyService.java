package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.model.Vacancy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {

    public Vacancy createVacancy(Vacancy vacancy) {
        // тут создаем новую вакансию
        // на вход приходит вакансия которую ввел работодатель
        // потом она должна сохраниться (но пока просто возвращаем ее)
        return vacancy;
    }

    public Optional<Vacancy> updateVacancy(Long id, Vacancy vacancy) {
        // здесь обновляем вакансию
        // по id ищем старую и заменяем на новую
        // пока логики нет, просто заглушка
        return Optional.empty();
    }

    public boolean deleteVacancy(Long id) {
        // удаляем вакансию по id
        // по идее нужно удалить из базы
        // пока просто возвращаем false потому что ничего не делаем
        return false;
    }

    public List<Vacancy> getAllActiveVacancies() {
        // получаем все активные вакансии
        // например где isActive = true
        // пока просто пустой список
        return List.of();
    }

    public List<Vacancy> getVacanciesByCategory(String category) {
        // ищем вакансии по категории (например IT)
        // фильтруем по category
        return List.of();
    }
}