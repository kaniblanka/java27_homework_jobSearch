package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.VacancyNotFoundException;
import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyDao vacancyDao;

    private VacancyDto mapToDto(Vacancy vacancy) {
        return new VacancyDto(
                vacancy.getId(),
                vacancy.getName(),
                vacancy.getDescription(),
                vacancy.getCategory() != null ? vacancy.getCategory().getId() : null,
                vacancy.getSalary(),
                vacancy.getExpFrom(),
                vacancy.getExpTo(),
                vacancy.getIsActive(),
                vacancy.getAuthor() != null ? vacancy.getAuthor().getId() : null,
                vacancy.getCreatedDate(),
                vacancy.getUpdateTime()
        );
    }

    private Vacancy mapToModel(VacancyDto vacancyDto) {
        Vacancy vacancy = new Vacancy();
        vacancy.setId(vacancyDto.getId());
        vacancy.setName(vacancyDto.getName());
        vacancy.setDescription(vacancyDto.getDescription());

        Category category = new Category();
        category.setId(vacancyDto.getCategoryId());
        vacancy.setCategory(category);

        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setExpFrom(vacancyDto.getExpFrom());
        vacancy.setExpTo(vacancyDto.getExpTo());
        vacancy.setIsActive(vacancyDto.getIsActive());

        User author = new User();
        author.setId(vacancyDto.getAuthorId());
        vacancy.setAuthor(author);

        vacancy.setCreatedDate(vacancyDto.getCreatedDate());
        vacancy.setUpdateTime(vacancyDto.getUpdateTime());

        return vacancy;
    }

    @Override
    public VacancyDto createVacancy(VacancyDto vacancyDto) throws CreateEntryException {
        log.info("Creating vacancy with name='{}'", vacancyDto.getName());

        try {
            Vacancy vacancy = mapToModel(vacancyDto);
            vacancy.setCreatedDate(LocalDateTime.now());
            vacancy.setUpdateTime(LocalDateTime.now());

            vacancyDao.create(vacancy);

            log.info("Vacancy created successfully with name='{}'", vacancyDto.getName());
            return mapToDto(vacancy);
        } catch (Exception e) {
            log.error("Error while creating vacancy with name='{}'", vacancyDto.getName(), e);
            throw new CreateEntryException("Vacancy was not created");
        }
    }

    @Override
    public VacancyDto updateVacancy(Long id, VacancyDto vacancyDto) throws VacancyNotFoundException, UpdateEntryException {
        log.info("Updating vacancy with id={}", id);

        vacancyDao.findById(id).orElseThrow(VacancyNotFoundException::new);

        Vacancy vacancy = mapToModel(vacancyDto);
        vacancy.setUpdateTime(LocalDateTime.now());

        boolean updated = vacancyDao.update(id, vacancy);

        if (!updated) {
            log.error("Vacancy was not updated, id={}", id);
            throw new UpdateEntryException("Vacancy was not updated");
        }

        log.info("Vacancy updated successfully, id={}", id);

        return vacancyDao.findById(id)
                .map(this::mapToDto)
                .orElseThrow(VacancyNotFoundException::new);
    }

    @Override
    public void deleteVacancy(Long id) throws VacancyNotFoundException, DeleteEntryException {
        log.info("Deleting vacancy with id={}", id);

        vacancyDao.findById(id).orElseThrow(VacancyNotFoundException::new);

        boolean deleted = vacancyDao.deleteById(id);

        if (!deleted) {
            log.error("Vacancy was not deleted, id={}", id);
            throw new DeleteEntryException("Vacancy was not deleted");
        }

        log.info("Vacancy deleted successfully, id={}", id);
    }

    @Override
    public List<VacancyDto> getAllActiveVacancies() {
        log.info("Getting all active vacancies");

        List<VacancyDto> vacancies = vacancyDao.findActive()
                .stream()
                .map(this::mapToDto)
                .toList();

        log.info("Found {} active vacancies", vacancies.size());
        return vacancies;
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(Long categoryId) {
        log.info("Getting vacancies by categoryId={}", categoryId);

        List<VacancyDto> vacancies = vacancyDao.findByCategory(categoryId)
                .stream()
                .map(this::mapToDto)
                .toList();

        log.info("Found {} vacancies for categoryId={}", vacancies.size(), categoryId);
        return vacancies;
    }

    @Override
    public VacancyDto getVacancyById(Long id) throws VacancyNotFoundException {
        log.info("Getting vacancy by id={}", id);

        VacancyDto vacancyDto = vacancyDao.findById(id)
                .map(this::mapToDto)
                .orElseThrow(VacancyNotFoundException::new);

        log.info("Vacancy found, id={}", id);
        return vacancyDto;
    }

    @Override
    public List<VacancyDto> getVacanciesByAuthorId(Long authorId) {
        log.info("Getting vacancies by authorId={}", authorId);

        List<VacancyDto> vacancies = vacancyDao.findByAuthorId(authorId)
                .stream()
                .map(this::mapToDto)
                .toList();

        log.info("Found {} vacancies for authorId={}", vacancies.size(), authorId);
        return vacancies;
    }
}