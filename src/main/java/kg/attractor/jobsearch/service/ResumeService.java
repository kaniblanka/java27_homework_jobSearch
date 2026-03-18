package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.model.Resume;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {

    public Resume createResume(Resume resume) {
        // создаем резюме
        // пользователь отправляет данные
        // мы должны сохранить
        return resume;
    }

    public Optional<Resume> updateResume(Long id, Resume resume) {
        // обновляем резюме по id
        // ищем старое и заменяем
        return Optional.empty();
    }

    public boolean deleteResume(Long id) {
        // удаляем резюме
        // по идее удаляется из базы
        return false;
    }

    public List<Resume> getAllResumes() {
        // получаем все резюме
        // например работодатель их смотрит
        return List.of();
    }

    public List<Resume> getResumesByCategory(String category) {
        // фильтр резюме по категории
        // например IT, Design и тд
        return List.of();
    }
}