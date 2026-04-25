package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.VacancyNotFoundException;
import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.repository.VacancyRepository;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;

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

        if (vacancyDto.getCategoryId() != null) {
            Category category = new Category();
            category.setId(vacancyDto.getCategoryId());
            vacancy.setCategory(category);
        }

        vacancy.setSalary(vacancyDto.getSalary());
        vacancy.setExpFrom(vacancyDto.getExpFrom());
        vacancy.setExpTo(vacancyDto.getExpTo());
        vacancy.setIsActive(vacancyDto.getIsActive());

        if (vacancyDto.getAuthorId() != null) {
            User author = new User();
            author.setId(vacancyDto.getAuthorId());
            vacancy.setAuthor(author);
        }

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

            Vacancy saved = vacancyRepository.save(vacancy);

            log.info("Vacancy created successfully with id={}", saved.getId());
            return mapToDto(saved);
        } catch (Exception e) {
            log.error("Error while creating vacancy with name='{}'", vacancyDto.getName(), e);
            throw new CreateEntryException("Vacancy was not created");
        }
    }

    @Override
    public VacancyDto updateVacancy(Long id, VacancyDto vacancyDto) throws VacancyNotFoundException, UpdateEntryException {
        log.info("Updating vacancy with id={}", id);

        Vacancy existing = vacancyRepository.findById(id)
                .orElseThrow(VacancyNotFoundException::new);

        try {
            existing.setName(vacancyDto.getName());
            existing.setDescription(vacancyDto.getDescription());

            if (vacancyDto.getCategoryId() != null) {
                Category category = new Category();
                category.setId(vacancyDto.getCategoryId());
                existing.setCategory(category);
            }

            existing.setSalary(vacancyDto.getSalary());
            existing.setExpFrom(vacancyDto.getExpFrom());
            existing.setExpTo(vacancyDto.getExpTo());
            existing.setIsActive(vacancyDto.getIsActive());

            if (vacancyDto.getAuthorId() != null) {
                User author = new User();
                author.setId(vacancyDto.getAuthorId());
                existing.setAuthor(author);
            }

            existing.setUpdateTime(LocalDateTime.now());

            Vacancy updated = vacancyRepository.save(existing);

            log.info("Vacancy updated successfully, id={}", id);
            return mapToDto(updated);
        } catch (Exception e) {
            log.error("Vacancy was not updated, id={}", id, e);
            throw new UpdateEntryException("Vacancy was not updated");
        }
    }

    @Override
    public void deleteVacancy(Long id) throws VacancyNotFoundException, DeleteEntryException {
        log.info("Deleting vacancy with id={}", id);

        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(VacancyNotFoundException::new);

        try {
            vacancyRepository.delete(vacancy);
            log.info("Vacancy deleted successfully, id={}", id);
        } catch (Exception e) {
            log.error("Vacancy was not deleted, id={}", id, e);
            throw new DeleteEntryException("Vacancy was not deleted");
        }
    }

    @Override
    public Page<VacancyDto> getAllActiveVacancies(Pageable pageable, String sort) {
        log.info("Getting all active vacancies with sort={}", sort);

        Page<Vacancy> vacancies;

        if ("responses".equalsIgnoreCase(sort)) {
            vacancies = vacancyRepository.findAllActiveOrderByResponsesCount(pageable);
        } else {
            vacancies = vacancyRepository.findByIsActiveTrueOrderByCreatedDateDesc(pageable);
        }

        return vacancies.map(this::mapToDto);
    }

    @Override
    public Page<VacancyDto> getVacanciesByCategory(Long categoryId, Pageable pageable) {
        log.info("Getting vacancies by categoryId={}", categoryId);
        return vacancyRepository.findByCategoryId(categoryId, pageable)
                .map(this::mapToDto);
    }

    @Override
    public VacancyDto getVacancyById(Long id) throws VacancyNotFoundException {
        log.info("Getting vacancy by id={}", id);

        Vacancy vacancy = vacancyRepository.findById(id)
                .orElseThrow(VacancyNotFoundException::new);

        return mapToDto(vacancy);
    }

    @Override
    public Page<VacancyDto> getVacanciesByAuthorId(Long authorId, Pageable pageable, String sort) {
        log.info("Getting vacancies by authorId={} with sort={}", authorId, sort);

        Page<Vacancy> vacancies;

        if ("responses".equalsIgnoreCase(sort)) {
            vacancies = vacancyRepository.findByAuthorIdOrderByResponsesCount(authorId, pageable);
        } else {
            vacancies = vacancyRepository.findByAuthorIdOrderByCreatedDateDesc(authorId, pageable);
        }

        return vacancies.map(this::mapToDto);
    }
}