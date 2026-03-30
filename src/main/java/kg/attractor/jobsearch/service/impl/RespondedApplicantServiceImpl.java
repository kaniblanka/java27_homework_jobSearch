package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.RespondedApplicantDao;
import kg.attractor.jobsearch.model.RespondedApplicant;
import kg.attractor.jobsearch.service.RespondedApplicantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RespondedApplicantServiceImpl implements RespondedApplicantService {

    private final RespondedApplicantDao respondedApplicantDao;

    @Override
    public RespondedApplicant respondToVacancy(RespondedApplicant respondedApplicant) {
        log.info("Creating response for vacancyId={}, resumeId={}",
                respondedApplicant.getVacancyId(),
                respondedApplicant.getResumeId());

        Long id = respondedApplicantDao.create(respondedApplicant);
        respondedApplicant.setId(id);

        log.info("Response created successfully with id={}", id);
        return respondedApplicant;
    }

    @Override
    public List<RespondedApplicant> getRespondedApplicantsByVacancyId(Long vacancyId) {
        log.info("Getting responded applicants by vacancyId={}", vacancyId);

        List<RespondedApplicant> responses = respondedApplicantDao.findByVacancyId(vacancyId);

        log.info("Found {} responses for vacancyId={}", responses.size(), vacancyId);
        return responses;
    }
}