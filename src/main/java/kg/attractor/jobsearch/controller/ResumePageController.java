package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("resumes")
@RequiredArgsConstructor
public class ResumePageController {

    private final ResumeService resumeService;

    @GetMapping
    public String getResumes(Model model, Pageable pageable) {
        Page<ResumeDto> resumesPage = resumeService.getAllResumes(pageable);

        model.addAttribute("resumesPage", resumesPage);
        model.addAttribute("resumes", resumesPage.getContent());

        return "resumes/all-resumes";
    }
}