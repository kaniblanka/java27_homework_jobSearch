package kg.attractor.jobsearch.service.impl;

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
import kg.attractor.jobsearch.repository.CategoryRepository;
import kg.attractor.jobsearch.repository.ResumeRepository;
import kg.attractor.jobsearch.repository.UserRepository;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

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
            userRepository.findById(resume.getApplicant().getId()).ifPresent(user -> {
                dto.setApplicantName(user.getName() + " " + user.getSurname());
            });
        }

        if (resume.getCategory() != null) {
            categoryRepository.findById(resume.getCategory().getId()).ifPresent(category -> {
                dto.setCategoryName(category.getName());
            });
        }

        return dto;
    }

    @Override
    public ResumeDto createResume(ResumeCreateDto dto) throws CreateEntryException {
        try {
            Resume resume = new Resume();

            if (dto.getApplicantId() != null) {
                User applicant = new User();
                applicant.setId(dto.getApplicantId());
                resume.setApplicant(applicant);
            }

            if (dto.getCategoryId() != null) {
                Category category = new Category();
                category.setId(dto.getCategoryId());
                resume.setCategory(category);
            }

            resume.setName(dto.getName());
            resume.setSalary(dto.getSalary());
            resume.setIsActive(dto.getIsActive());
            resume.setCreatedDate(LocalDateTime.now());
            resume.setUpdateTime(LocalDateTime.now());

            if (dto.getEducationInfoList() != null) {
                for (EducationInfo educationInfo : dto.getEducationInfoList()) {
                    educationInfo.setResume(resume);
                }
                resume.setEducationInfoList(dto.getEducationInfoList());
            }

            if (dto.getWorkExperienceInfoList() != null) {
                for (WorkExperienceInfo workExperienceInfo : dto.getWorkExperienceInfoList()) {
                    workExperienceInfo.setResume(resume);
                }
                resume.setWorkExperienceInfoList(dto.getWorkExperienceInfoList());
            }

            Resume saved = resumeRepository.save(resume);

            log.debug("Created resume with id = {}", saved.getId());
            return mapToDto(saved);
        } catch (Exception e) {
            log.error("Resume was not created", e);
            throw new CreateEntryException("Resume was not created");
        }
    }

    @Override
    public ResumeDto updateResume(Long id, ResumeCreateDto dto) throws ResumeNotFoundException, UpdateEntryException {
        Resume existingResume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        try {
            if (dto.getApplicantId() != null) {
                User applicant = new User();
                applicant.setId(dto.getApplicantId());
                existingResume.setApplicant(applicant);
            }

            if (dto.getCategoryId() != null) {
                Category category = new Category();
                category.setId(dto.getCategoryId());
                existingResume.setCategory(category);
            }

            existingResume.setName(dto.getName());
            existingResume.setSalary(dto.getSalary());
            existingResume.setIsActive(dto.getIsActive());
            existingResume.setUpdateTime(LocalDateTime.now());

            Resume updated = resumeRepository.save(existingResume);

            log.debug("Updated resume with id = {}", id);
            return mapToDto(updated);
        } catch (Exception e) {
            log.error("Resume was not updated, id={}", id, e);
            throw new UpdateEntryException("Resume was not updated");
        }
    }

    @Override
    public void deleteResume(Long id) throws ResumeNotFoundException, DeleteEntryException {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        try {
            resumeRepository.delete(resume);
            log.debug("Deleted resume with id = {}", id);
        } catch (Exception e) {
            log.error("Resume was not deleted, id={}", id, e);
            throw new DeleteEntryException("Resume was not deleted");
        }
    }

    @Override
    public Page<ResumeDto> getAllResumes(Pageable pageable) {
        return resumeRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    @Override
    public Page<ResumeDto> getResumesByCategory(Long categoryId, Pageable pageable) {
        return resumeRepository.findByCategoryId(categoryId, pageable)
                .map(this::mapToDto);
    }

    @Override
    public Page<ResumeDto> getResumesByApplicantId(Long applicantId, Pageable pageable) {
        return resumeRepository.findByApplicantId(applicantId, pageable)
                .map(this::mapToDto);
    }

    @Override
    public ResumeDto getResumeById(Long id) throws ResumeNotFoundException {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(ResumeNotFoundException::new);

        return mapToDto(resume);
    }
}