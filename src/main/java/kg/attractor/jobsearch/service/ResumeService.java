package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.ResumeNotFoundException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.model.Resume;

import java.util.List;

public interface ResumeService {

    ResumeDto createResume(ResumeCreateDto dto) throws CreateEntryException;

    ResumeDto updateResume(Long id, Resume resume)
            throws ResumeNotFoundException, UpdateEntryException;

    void deleteResume(Long id)
            throws ResumeNotFoundException, DeleteEntryException;

    List<ResumeDto> getAllResumes();

    List<ResumeDto> getResumesByCategory(Long categoryId);

    List<ResumeDto> getResumesByApplicantId(Long applicantId);

    ResumeDto getResumeById(Long id) throws ResumeNotFoundException;
}