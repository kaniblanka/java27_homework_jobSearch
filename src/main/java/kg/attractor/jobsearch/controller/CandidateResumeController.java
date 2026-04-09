package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.*;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.UserNotFoundException;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("candidate/resumes")
@RequiredArgsConstructor
public class CandidateResumeController {

    private final ResumeService resumeService;
    private final UserService userService;

    @GetMapping("create")
    public String createPage(Model model) {
        model.addAttribute("resumeCreateWebDto", new ResumeCreateWebDto());
        return "resumes/create-resume";
    }

    @PostMapping("create")
    public String createResume(@Valid @ModelAttribute ResumeCreateWebDto dto,
                               BindingResult bindingResult,
                               Principal principal,
                               Model model)
            throws UserNotFoundException, CreateEntryException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("resumeCreateWebDto", dto);
            return "resumes/create-resume";
        }

        UserDto user = userService.findByEmail(principal.getName());

        ResumeCreateDto resume = new ResumeCreateDto();
        resume.setApplicantId(user.getId());
        resume.setName(dto.getName());
        resume.setCategoryId(dto.getCategoryId());
        resume.setSalary(dto.getSalary());
        resume.setIsActive(true);

        resumeService.createResume(resume);

        return "redirect:/profile/resumes";
    }
}