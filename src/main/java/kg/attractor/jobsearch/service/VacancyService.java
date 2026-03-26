package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.VacancyNotFoundException;

import java.util.List;

public interface VacancyService {
    VacancyDto createVacancy(VacancyDto vacancyDto) throws CreateEntryException;
    VacancyDto updateVacancy(Long id, VacancyDto vacancyDto) throws VacancyNotFoundException, UpdateEntryException;
    void deleteVacancy(Long id) throws VacancyNotFoundException, DeleteEntryException;
    List<VacancyDto> getAllActiveVacancies();
    List<VacancyDto> getVacanciesByCategory(Long categoryId);
    VacancyDto getVacancyById(Long id) throws VacancyNotFoundException;
}