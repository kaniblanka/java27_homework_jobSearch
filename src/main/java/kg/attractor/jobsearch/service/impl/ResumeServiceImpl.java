package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.EducationInfoDao;
import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dao.WorkExperienceInfoDao;
import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.ResumeNotFoundException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.model.EducationInfo;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.WorkExperienceInfo;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;
    private final EducationInfoDao educationInfoDao;
    private final WorkExperienceInfoDao workExperienceInfoDao;

    @Override
    public Resume createResume(ResumeCreateDto dto) throws CreateEntryException {
        try {
            Resume resume = new Resume();
            resume.setApplicantId(dto.getApplicantId());
            resume.setName(dto.getName());
            resume.setCategoryId(dto.getCategoryId());
            resume.setSalary(dto.getSalary());
            resume.setIsActive(dto.getIsActive());
            resume.setCreatedDate(LocalDateTime.now());
            resume.setUpdateTime(LocalDateTime.now());

            Long resumeId = resumeDao.create(resume);

            if (resumeId == null) {
                throw new CreateEntryException("Resume was not created");
            }

            resume.setId(resumeId);

            log.debug("Created resume with id = {}", resumeId);

            if (dto.getEducationInfoList() != null) {
                for (EducationInfo educationInfo : dto.getEducationInfoList()) {
                    educationInfo.setResumeId(resumeId);
                    educationInfoDao.create(educationInfo);
                }
            }

            if (dto.getWorkExperienceInfoList() != null) {
                for (WorkExperienceInfo workExperienceInfo : dto.getWorkExperienceInfoList()) {
                    workExperienceInfo.setResumeId(resumeId);
                    workExperienceInfoDao.create(workExperienceInfo);
                }
            }

            return resume;
        } catch (CreateEntryException e) {
            throw e;
        } catch (Exception e) {
            throw new CreateEntryException("Resume was not created");
        }
    }

    @Override
    public Resume updateResume(Long id, Resume resume) throws ResumeNotFoundException, UpdateEntryException {
        resumeDao.findById(id).orElseThrow(ResumeNotFoundException::new);

        resume.setUpdateTime(LocalDateTime.now());

        boolean updated = resumeDao.update(id, resume);

        if (!updated) {
            throw new UpdateEntryException("Resume was not updated");
        }

        log.debug("Updated resume with id = {}", id);

        return resumeDao.findById(id)
                .orElseThrow(ResumeNotFoundException::new);
    }

    @Override
    public void deleteResume(Long id) throws ResumeNotFoundException, DeleteEntryException {
        resumeDao.findById(id).orElseThrow(ResumeNotFoundException::new);

        educationInfoDao.deleteByResumeId(id);
        workExperienceInfoDao.deleteByResumeId(id);

        boolean deleted = resumeDao.deleteById(id);

        if (!deleted) {
            throw new DeleteEntryException("Resume was not deleted");
        }

        log.debug("Deleted resume with id = {}", id);
    }

    @Override
    public List<Resume> getAllResumes() {
        return resumeDao.findAll();
    }

    @Override
    public List<Resume> getResumesByCategory(Long categoryId) {
        return resumeDao.findByCategory(categoryId);
    }

    @Override
    public List<Resume> getResumesByApplicantId(Long applicantId) {
        return resumeDao.findByApplicantId(applicantId);
    }

    @Override
    public Resume getResumeById(Long id) throws ResumeNotFoundException {
        return resumeDao.findById(id)
                .orElseThrow(ResumeNotFoundException::new);
    }
}