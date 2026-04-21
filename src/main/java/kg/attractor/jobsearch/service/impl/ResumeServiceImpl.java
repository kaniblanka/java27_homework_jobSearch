package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.dao.CategoryDao;
import kg.attractor.jobsearch.dao.EducationInfoDao;
import kg.attractor.jobsearch.dao.ResumeDao;
import kg.attractor.jobsearch.dao.UserDao;
import kg.attractor.jobsearch.dao.WorkExperienceInfoDao;
import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.ResumeNotFoundException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.model.Category;
import kg.attractor.jobsearch.model.EducationInfo;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.model.User;
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
    private final UserDao userDao;
    private final CategoryDao categoryDao;

    private ResumeDto mapToDto(Resume resume) {
        ResumeDto dto = new ResumeDto();

        dto.setId(resume.getId());
        dto.setApplicantId(resume.getApplicant() != null ? resume.getApplicant().getId() : null);
        dto.setName(resume.getName());
        dto.setCategoryId(resume.getCategory() != null ? resume.getCategory().getId() : null);
        dto.setSalary(resume.getSalary());
        dto.setIsActive(resume.getIsActive());
        dto.setCreatedDate(resume.getCreatedDate());
        dto.setUpdateTime(resume.getUpdateTime());

        if (resume.getApplicant() != null) {
            userDao.findById(resume.getApplicant().getId()).ifPresent(user -> {
                dto.setApplicantName(user.getName() + " " + user.getSurname());
            });
        }

        if (resume.getCategory() != null) {
            categoryDao.findById(resume.getCategory().getId()).ifPresent(category -> {
                dto.setCategoryName(category.getName());
            });
        }

        return dto;
    }

    @Override
    public ResumeDto createResume(ResumeCreateDto dto) throws CreateEntryException {
        try {
            Resume resume = new Resume();

            User applicant = new User();
            applicant.setId(dto.getApplicantId());
            resume.setApplicant(applicant);

            Category category = new Category();
            category.setId(dto.getCategoryId());
            resume.setCategory(category);

            resume.setName(dto.getName());
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

            return mapToDto(resume);
        } catch (CreateEntryException e) {
            throw e;
        } catch (Exception e) {
            throw new CreateEntryException("Resume was not created");
        }
    }

    @Override
    public ResumeDto updateResume(Long id, ResumeCreateDto dto) throws ResumeNotFoundException, UpdateEntryException {
        Resume existingResume = resumeDao.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        User applicant = new User();
        applicant.setId(dto.getApplicantId());
        existingResume.setApplicant(applicant);

        Category category = new Category();
        category.setId(dto.getCategoryId());
        existingResume.setCategory(category);

        existingResume.setName(dto.getName());
        existingResume.setSalary(dto.getSalary());
        existingResume.setIsActive(dto.getIsActive());
        existingResume.setUpdateTime(LocalDateTime.now());

        boolean updated = resumeDao.update(id, existingResume);
        if (!updated) {
            throw new UpdateEntryException("Resume was not updated");
        }

        log.debug("Updated resume with id = {}", id);

        Resume updatedResume = resumeDao.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        return mapToDto(updatedResume);
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
    public List<ResumeDto> getAllResumes() {
        return resumeDao.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<ResumeDto> getResumesByCategory(Long categoryId) {
        return resumeDao.findByCategory(categoryId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<ResumeDto> getResumesByApplicantId(Long applicantId) {
        return resumeDao.findByApplicantId(applicantId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public ResumeDto getResumeById(Long id) throws ResumeNotFoundException {
        Resume resume = resumeDao.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        return mapToDto(resume);
    }
}