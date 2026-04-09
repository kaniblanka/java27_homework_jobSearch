package kg.attractor.jobsearch.controller.api;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.VacancyNotFoundException;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vacancies")
@RequiredArgsConstructor
public class ApiVacancyController {

    private final VacancyService vacancyService;

    @PostMapping
    public VacancyDto createVacancy(@Valid @RequestBody VacancyDto vacancyDto) throws CreateEntryException {
        return vacancyService.createVacancy(vacancyDto);
    }

    @PutMapping("{id}")
    public VacancyDto updateVacancy(@PathVariable Long id,
                                    @Valid @RequestBody VacancyDto vacancyDto) throws UpdateEntryException, VacancyNotFoundException {
        return vacancyService.updateVacancy(id, vacancyDto);
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteVacancy(@PathVariable Long id) throws DeleteEntryException, VacancyNotFoundException {
        vacancyService.deleteVacancy(id);
        return HttpStatus.OK;
    }

    @GetMapping
    public List<VacancyDto> getAllActiveVacancies() {
        return vacancyService.getAllActiveVacancies();
    }

    @GetMapping("{id}")
    public VacancyDto getVacancyById(@PathVariable Long id) throws VacancyNotFoundException {
        return vacancyService.getVacancyById(id);
    }

    @GetMapping("category/{categoryId}")
    public List<VacancyDto> getVacanciesByCategory(@PathVariable Long categoryId) {
        return vacancyService.getVacanciesByCategory(categoryId);
    }
}