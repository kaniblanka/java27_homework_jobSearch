package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.EducationInfoDao;
import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dao.WorkExperienceInfoDao;
import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.model.EducationInfo;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.WorkExperienceInfo;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDao resumeDao;
    private final EducationInfoDao educationInfoDao;
    private final WorkExperienceInfoDao workExperienceInfoDao;

    @Override
    public Resume createResume(ResumeCreateDto dto) {
        Resume resume = new Resume();
        resume.setApplicantId(dto.getApplicantId());
        resume.setName(dto.getName());
        resume.setCategoryId(dto.getCategoryId());
        resume.setSalary(dto.getSalary());
        resume.setIsActive(dto.getIsActive());
        resume.setCreatedDate(LocalDateTime.now());
        resume.setUpdateTime(LocalDateTime.now());

        Long resumeId = resumeDao.create(resume);
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
    }

    @Override
    public Optional<Resume> updateResume(Long id, Resume resume) {
        resume.setUpdateTime(LocalDateTime.now());

        boolean updated = resumeDao.update(id, resume);

        if (updated) {
            log.debug("Updated resume with id = {}", id);
            return resumeDao.findById(id);
        }

        log.debug("Resume with id = {} not found for update", id);
        return Optional.empty();
    }

    @Override
    public boolean deleteResume(Long id) {
        educationInfoDao.deleteByResumeId(id);
        workExperienceInfoDao.deleteByResumeId(id);

        boolean deleted = resumeDao.deleteById(id);

        if (deleted) {
            log.debug("Deleted resume with id = {}", id);
        } else {
            log.debug("Resume with id = {} not found for delete", id);
        }

        return deleted;
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
    public Optional<Resume> getResumeById(Long id) {
        return resumeDao.findById(id);
    }
}