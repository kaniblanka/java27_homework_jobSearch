package kg.attractor.jobsearch.controller.api;

import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.DeleteEntryException;
import kg.attractor.jobsearch.exception.ResumeNotFoundException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ApiResumeController {

    private final ResumeService resumeService;

    @GetMapping
    public ResponseEntity<Page<ResumeDto>> getAllResumes(Pageable pageable) {
        return ResponseEntity.ok(resumeService.getAllResumes(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable Long id) throws ResumeNotFoundException {
        return ResponseEntity.ok(resumeService.getResumeById(id));
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<Page<ResumeDto>> getResumesByApplicantId(@PathVariable Long applicantId,
                                                                   Pageable pageable) {
        return ResponseEntity.ok(resumeService.getResumesByApplicantId(applicantId, pageable));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<ResumeDto>> getResumesByCategory(@PathVariable Long categoryId,
                                                                Pageable pageable) {
        return ResponseEntity.ok(resumeService.getResumesByCategory(categoryId, pageable));
    }

    @PostMapping
    public ResponseEntity<ResumeDto> createResume(@RequestBody ResumeCreateDto dto) throws CreateEntryException {
        return ResponseEntity.ok(resumeService.createResume(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResumeDto> updateResume(@PathVariable Long id,
                                                  @RequestBody ResumeCreateDto dto)
            throws ResumeNotFoundException, UpdateEntryException {
        return ResponseEntity.ok(resumeService.updateResume(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id)
            throws ResumeNotFoundException, DeleteEntryException {
        resumeService.deleteResume(id);
        return ResponseEntity.noContent().build();
    }
}