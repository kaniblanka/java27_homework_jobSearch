package kg.attractor.jobsearch.controller.api;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.VacancyNotFoundException;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vacancies")
@RequiredArgsConstructor
public class ApiVacancyController {

    private final VacancyService vacancyService;

    @GetMapping
    public ResponseEntity<Page<VacancyDto>> getAllActiveVacancies(
            Pageable pageable,
            @RequestParam(defaultValue = "date") String sort
    ) {
        return ResponseEntity.ok(vacancyService.getAllActiveVacancies(pageable, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacancyDto> getVacancyById(@PathVariable Long id) throws VacancyNotFoundException {
        return ResponseEntity.ok(vacancyService.getVacancyById(id));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<Page<VacancyDto>> getVacanciesByAuthorId(
            @PathVariable Long authorId,
            Pageable pageable,
            @RequestParam(defaultValue = "date") String sort
    ) {
        return ResponseEntity.ok(vacancyService.getVacanciesByAuthorId(authorId, pageable, sort));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<VacancyDto>> getVacanciesByCategory(
            @PathVariable Long categoryId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(vacancyService.getVacanciesByCategory(categoryId, pageable));
    }

    @PostMapping
    public ResponseEntity<VacancyDto> createVacancy(@RequestBody VacancyDto vacancyDto) throws CreateEntryException {
        return ResponseEntity.ok(vacancyService.createVacancy(vacancyDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacancyDto> updateVacancy(@PathVariable Long id,
                                                    @RequestBody VacancyDto vacancyDto)
            throws VacancyNotFoundException, UpdateEntryException {
        return ResponseEntity.ok(vacancyService.updateVacancy(id, vacancyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Long id)
            throws VacancyNotFoundException, DeleteEntryException {
        vacancyService.deleteVacancy(id);
        return ResponseEntity.noContent().build();
    }
}