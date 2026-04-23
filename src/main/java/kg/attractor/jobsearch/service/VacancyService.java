package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.VacancyNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VacancyService {

    VacancyDto createVacancy(VacancyDto vacancyDto) throws CreateEntryException;

    VacancyDto updateVacancy(Long id, VacancyDto vacancyDto) throws VacancyNotFoundException, UpdateEntryException;

    void deleteVacancy(Long id) throws VacancyNotFoundException, DeleteEntryException;

    Page<VacancyDto> getAllActiveVacancies(Pageable pageable, String sort);

    Page<VacancyDto> getVacanciesByCategory(Long categoryId, Pageable pageable);

    VacancyDto getVacancyById(Long id) throws VacancyNotFoundException;

    Page<VacancyDto> getVacanciesByAuthorId(Long authorId, Pageable pageable, String sort);
}