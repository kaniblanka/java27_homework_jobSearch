package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.model.RespondedApplicant;

import java.util.List;

public interface RespondedApplicantService {

    RespondedApplicant respondToVacancy(RespondedApplicant respondedApplicant);

    List<RespondedApplicant> getRespondedApplicantsByVacancyId(Long vacancyId);
}