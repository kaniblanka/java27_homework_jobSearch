package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.VacancyDao;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.model.Vacancy;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public VacancyDto createVacancy(VacancyDto vacancyDto) {
        Vacancy vacancy = mapToModel(vacancyDto);
        vacancy.setCreatedDate(LocalDateTime.now());
        vacancy.setUpdateTime(LocalDateTime.now());

        vacancyDao.create(vacancy);
        return mapToDto(vacancy);
    }

    @Override
    public Optional<VacancyDto> updateVacancy(Long id, VacancyDto vacancyDto) {
        Vacancy vacancy = mapToModel(vacancyDto);
        vacancy.setUpdateTime(LocalDateTime.now());

        boolean updated = vacancyDao.update(id, vacancy);

        if (updated) {
            return vacancyDao.findById(id).map(this::mapToDto);
        }

        return Optional.empty();
    }

    @Override
    public boolean deleteVacancy(Long id) {
        return vacancyDao.deleteById(id);
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
    public Optional<VacancyDto> getVacancyById(Long id) {
        return vacancyDao.findById(id).map(this::mapToDto);
    }
}