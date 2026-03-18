package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.model.RespondedApplicant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespondedApplicantService {

    public RespondedApplicant respondToVacancy(RespondedApplicant respondedApplicant) {
        // тут соискатель откликается на вакансию
        // сохраняем кто откликнулся и на какую вакансию
        return respondedApplicant;
    }

    public List<RespondedApplicant> getRespondedApplicantsByVacancyId(Long vacancyId) {
        // получаем всех кто откликнулся на вакансию
        // по id вакансии
        return List.of();
    }
}