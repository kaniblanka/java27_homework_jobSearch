package kg.attractor.jobsearch.controller.api;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.ResumeNotFoundException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/resumes")
@RequiredArgsConstructor
public class ApiResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public ResumeDto createResume(@Valid @RequestBody ResumeCreateDto dto) throws CreateEntryException {
        return resumeService.createResume(dto);
    }

    @PutMapping("{id}")
    public ResumeDto updateResume(@PathVariable Long id,
                                  @Valid @RequestBody Resume resume)
            throws ResumeNotFoundException, UpdateEntryException {
        return resumeService.updateResume(id, resume);
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteResume(@PathVariable Long id)
            throws ResumeNotFoundException, DeleteEntryException {
        resumeService.deleteResume(id);
        return HttpStatus.OK;
    }

    @GetMapping
    public List<ResumeDto> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("{id}")
    public ResumeDto getResumeById(@PathVariable Long id) throws ResumeNotFoundException {
        return resumeService.getResumeById(id);
    }

    @GetMapping("category/{categoryId}")
    public List<ResumeDto> getResumesByCategory(@PathVariable Long categoryId) {
        return resumeService.getResumesByCategory(categoryId);
    }

    @GetMapping("applicant/{applicantId}")
    public List<ResumeDto> getResumesByApplicantId(@PathVariable Long applicantId) {
        return resumeService.getResumesByApplicantId(applicantId);
    }
}