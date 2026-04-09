package kg.attractor.jobsearch.controller.api;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.model.RespondedApplicant;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/responses")
@RequiredArgsConstructor
public class ApiRespondedApplicantController {

    private final RespondedApplicantService respondedApplicantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespondedApplicant respondToVacancy(@Valid @RequestBody RespondedApplicant respondedApplicant) {
        return respondedApplicantService.respondToVacancy(respondedApplicant);
    }

    @GetMapping("vacancy/{vacancyId}")
    public List<RespondedApplicant> getRespondedApplicantsByVacancy(@PathVariable Long vacancyId) {
        return respondedApplicantService.getRespondedApplicantsByVacancyId(vacancyId);
    }
}