package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.model.Resume;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping
    public Resume createResume(@Valid @RequestBody ResumeCreateDto dto) {
        return resumeService.createResume(dto);
    }

    @PutMapping("{id}")
    public Optional<Resume> updateResume(@PathVariable Long id,
                                         @Valid @RequestBody Resume resume) {
        return resumeService.updateResume(id, resume);
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteResume(@PathVariable Long id) {
        return resumeService.deleteResume(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }

    @GetMapping
    public List<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("{id}")
    public Optional<Resume> getResumeById(@PathVariable Long id) {
        return resumeService.getResumeById(id);
    }

    @GetMapping("category/{categoryId}")
    public List<Resume> getResumesByCategory(@PathVariable Long categoryId) {
        return resumeService.getResumesByCategory(categoryId);
    }

    @GetMapping("applicant/{applicantId}")
    public List<Resume> getResumesByApplicantId(@PathVariable Long applicantId) {
        return resumeService.getResumesByApplicantId(applicantId);
    }
}