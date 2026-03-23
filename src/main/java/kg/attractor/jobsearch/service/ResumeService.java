package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.model.Resume;

import java.util.List;
import java.util.Optional;

public interface ResumeService {

    Resume createResume(ResumeCreateDto dto);

    Optional<Resume> updateResume(Long id, Resume resume);

    boolean deleteResume(Long id);

    List<Resume> getAllResumes();

    List<Resume> getResumesByCategory(Long categoryId);

    List<Resume> getResumesByApplicantId(Long applicantId);

    Optional<Resume> getResumeById(Long id);
}