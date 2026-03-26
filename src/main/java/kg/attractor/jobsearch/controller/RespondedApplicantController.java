package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.model.RespondedApplicant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("responses")
@RequiredArgsConstructor
public class RespondedApplicantController {

    @PostMapping
    public HttpStatus respondToVacancy(@Valid @RequestBody RespondedApplicant respondedApplicant) {
        return HttpStatus.OK;
    }

    @GetMapping("vacancy/{vacancyId}")
    public HttpStatus getRespondedApplicantsByVacancy(@PathVariable Long vacancyId) {
        return HttpStatus.OK;
    }
}