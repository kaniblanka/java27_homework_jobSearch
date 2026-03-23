package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;

    @PostMapping
    public VacancyDto createVacancy(@RequestBody VacancyDto vacancyDto) {
        return vacancyService.createVacancy(vacancyDto);
    }

    @PutMapping("{id}")
    public Optional<VacancyDto> updateVacancy(@PathVariable Long id,
                                              @RequestBody VacancyDto vacancyDto) {
        return vacancyService.updateVacancy(id, vacancyDto);
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteVacancy(@PathVariable Long id) {
        return vacancyService.deleteVacancy(id)
                ? HttpStatus.OK
                : HttpStatus.NOT_FOUND;
    }

    @GetMapping
    public List<VacancyDto> getAllActiveVacancies() {
        return vacancyService.getAllActiveVacancies();
    }

    @GetMapping("{id}")
    public Optional<VacancyDto> getVacancyById(@PathVariable Long id) {
        return vacancyService.getVacancyById(id);
    }

    @GetMapping("category/{categoryId}")
    public List<VacancyDto> getVacanciesByCategory(@PathVariable Long categoryId) {
        return vacancyService.getVacanciesByCategory(categoryId);
    }
}