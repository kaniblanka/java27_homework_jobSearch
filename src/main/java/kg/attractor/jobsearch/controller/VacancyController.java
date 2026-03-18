package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyController {

    @PostMapping
    public HttpStatus createVacancy(@RequestBody Vacancy vacancy) {
        return HttpStatus.OK;
    }

    @PutMapping("{id}")
    public HttpStatus updateVacancy(@PathVariable Long id,
                                    @RequestBody Vacancy vacancy) {
        return HttpStatus.OK;
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteVacancy(@PathVariable Long id) {
        return HttpStatus.OK;
    }

    @GetMapping
    public HttpStatus getAllActiveVacancies() {
        return HttpStatus.OK;
    }

    @GetMapping("category/{category}")
    public HttpStatus getVacanciesByCategory(@PathVariable String category) {
        return HttpStatus.OK;
    }
}