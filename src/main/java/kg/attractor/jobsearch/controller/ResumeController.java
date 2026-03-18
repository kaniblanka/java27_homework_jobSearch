package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumeController {

    @PostMapping
    public HttpStatus createResume(@RequestBody Resume resume) {
        return HttpStatus.OK;
    }

    @PutMapping("{id}")
    public HttpStatus updateResume(@PathVariable Long id,
                                   @RequestBody Resume resume) {
        return HttpStatus.OK;
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteResume(@PathVariable Long id) {
        return HttpStatus.OK;
    }

    @GetMapping
    public HttpStatus getAllResumes() {
        return HttpStatus.OK;
    }

    @GetMapping("category/{category}")
    public HttpStatus getResumesByCategory(@PathVariable String category) {
        return HttpStatus.OK;
    }
}