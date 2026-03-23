package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;

import java.util.List;
import java.util.Optional;

public interface VacancyService {

    VacancyDto createVacancy(VacancyDto vacancyDto);

    Optional<VacancyDto> updateVacancy(Long id, VacancyDto vacancyDto);

    boolean deleteVacancy(Long id);

    List<VacancyDto> getAllActiveVacancies();

    List<VacancyDto> getVacanciesByCategory(Long categoryId);

    Optional<VacancyDto> getVacancyById(Long id);
}