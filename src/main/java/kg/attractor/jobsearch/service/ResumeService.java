package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.ResumeNotFoundException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.model.Resume;

import java.util.List;

public interface ResumeService {
    Resume createResume(ResumeCreateDto dto) throws CreateEntryException;
    Resume updateResume(Long id, Resume resume) throws ResumeNotFoundException, UpdateEntryException;
    void deleteResume(Long id) throws ResumeNotFoundException, DeleteEntryException;
    List<Resume> getAllResumes();
    List<Resume> getResumesByCategory(Long categoryId);
    List<Resume> getResumesByApplicantId(Long applicantId);
    Resume getResumeById(Long id) throws ResumeNotFoundException;
}