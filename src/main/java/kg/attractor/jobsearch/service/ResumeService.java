package kg.attractor.jobsearch.service;

import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.ResumeNotFoundException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ResumeService {

    ResumeDto createResume(ResumeCreateDto dto) throws CreateEntryException;

    ResumeDto updateResume(Long id, ResumeCreateDto dto) throws ResumeNotFoundException, UpdateEntryException;

    void deleteResume(Long id) throws ResumeNotFoundException, DeleteEntryException;

    Page<ResumeDto> getAllResumes(Pageable pageable);

    Page<ResumeDto> getResumesByCategory(Long categoryId, Pageable pageable);

    Page<ResumeDto> getResumesByApplicantId(Long applicantId, Pageable pageable);

    ResumeDto getResumeById(Long id) throws ResumeNotFoundException;
}