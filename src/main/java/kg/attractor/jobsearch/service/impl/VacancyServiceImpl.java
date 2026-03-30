package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.VacancyNotFoundException;
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
                vacancy.getCategoryId(),
                vacancy.getSalary(),
                vacancy.getExpFrom(),
                vacancy.getExpTo(),
                vacancy.getIsActive(),
                vacancy.getAuthorId(),
                vacancy.getCreatedDate(),
                vacancy.getUpdateTime()
        );
    }

    private Vacancy mapToModel(VacancyDto vacancyDto) {
        return new Vacancy(
                vacancyDto.getId(),
                vacancyDto.getName(),
                vacancyDto.getDescription(),
                vacancyDto.getCategoryId(),
                vacancyDto.getSalary(),
                vacancyDto.getExpFrom(),
                vacancyDto.getExpTo(),
                vacancyDto.getIsActive(),
                vacancyDto.getAuthorId(),
                vacancyDto.getCreatedDate(),
                vacancyDto.getUpdateTime()
        );
    }

    @Override
    public VacancyDto createVacancy(VacancyDto vacancyDto) throws CreateEntryException {
        try {
            Vacancy vacancy = mapToModel(vacancyDto);
            vacancy.setCreatedDate(LocalDateTime.now());
            vacancy.setUpdateTime(LocalDateTime.now());

            vacancyDao.create(vacancy);

            return mapToDto(vacancy);
        } catch (Exception e) {
            throw new CreateEntryException("Vacancy was not created");
        }
    }

    @Override
    public VacancyDto updateVacancy(Long id, VacancyDto vacancyDto) throws VacancyNotFoundException, UpdateEntryException {
        vacancyDao.findById(id).orElseThrow(VacancyNotFoundException::new);

        Vacancy vacancy = mapToModel(vacancyDto);
        vacancy.setUpdateTime(LocalDateTime.now());

        boolean updated = vacancyDao.update(id, vacancy);

        if (!updated) {
            throw new UpdateEntryException("Vacancy was not updated");
        }

        return vacancyDao.findById(id)
                .map(this::mapToDto)
                .orElseThrow(VacancyNotFoundException::new);
    }

    @Override
    public void deleteVacancy(Long id) throws VacancyNotFoundException, DeleteEntryException {
        vacancyDao.findById(id).orElseThrow(VacancyNotFoundException::new);

        boolean deleted = vacancyDao.deleteById(id);

        if (!deleted) {
            throw new DeleteEntryException("Vacancy was not deleted");
        }
    }

    @Override
    public List<VacancyDto> getAllActiveVacancies() {
        return vacancyDao.findActive()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<VacancyDto> getVacanciesByCategory(Long categoryId) {
        return vacancyDao.findByCategory(categoryId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public VacancyDto getVacancyById(Long id) throws VacancyNotFoundException {
        return vacancyDao.findById(id)
                .map(this::mapToDto)
                .orElseThrow(VacancyNotFoundException::new);
    }

    log.info("Creating vacancy");
log.info("Updating vacancy with id: {}", id);
log.info("Deleting vacancy with id: {}", id);
log.info("Getting all active vacancies");
log.info("Getting vacancies by category id: {}", categoryId);
log.info("Getting vacancy by id: {}", id);
}