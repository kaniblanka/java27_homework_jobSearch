package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.exception.UserNotFoundException;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public String allResumesPage(Model model, Principal principal) throws UserNotFoundException {
        UserDto user = userService.findByEmail(principal.getName());

        model.addAttribute("user", user);
        model.addAttribute("resumes", resumeService.getAllResumes());

        return "resumes/all-resumes";
    }
}