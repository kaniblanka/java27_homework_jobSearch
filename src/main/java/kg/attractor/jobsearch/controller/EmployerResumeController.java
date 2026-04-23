package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.exception.UserNotFoundException;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("employer/resumes")
@RequiredArgsConstructor
public class EmployerResumeController {

    private final ResumeService resumeService;
    private final UserService userService;

    @GetMapping
    public String allResumesPage(Model model,
                                 Principal principal,
                                 Pageable pageable) throws UserNotFoundException {
        UserDto user = userService.findByEmail(principal.getName());

        Page<ResumeDto> resumesPage = resumeService.getAllResumes(pageable);

        model.addAttribute("user", user);
        model.addAttribute("resumesPage", resumesPage);
        model.addAttribute("resumes", resumesPage.getContent());

        return "resumes/all-resumes";
    }
}