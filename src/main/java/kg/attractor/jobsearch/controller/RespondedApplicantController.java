package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.model.RespondedApplicant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("responses")
@RequiredArgsConstructor
public class RespondedApplicantController {

    @PostMapping
    public HttpStatus respondToVacancy(@RequestBody RespondedApplicant respondedApplicant) {
        return HttpStatus.OK;
    }

    @GetMapping("vacancy/{vacancyId}")
    public HttpStatus getRespondedApplicantsByVacancy(@PathVariable Long vacancyId) {
        return HttpStatus.OK;
    }
}