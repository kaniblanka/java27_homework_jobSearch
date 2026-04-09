package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.dto.ResumeCreateWebDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.UserNotFoundException;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("candidate/resumes")
@RequiredArgsConstructor
public class CandidateResumeController {

    private final ResumeService resumeService;
    private final UserService userService;

    @GetMapping("create")
    public String createResumePage(Model model) {
        model.addAttribute("resumeCreateWebDto", new ResumeCreateWebDto());
        return "resumes/create-resume";
    }

    @PostMapping("create")
    public String createResume(@Valid @ModelAttribute ResumeCreateWebDto resumeCreateWebDto,
                               BindingResult bindingResult,
                               Principal principal,
                               Model model) throws UserNotFoundException, CreateEntryException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("resumeCreateWebDto", resumeCreateWebDto);
            return "resumes/create-resume";
        }

        UserDto currentUser = userService.findByEmail(principal.getName());

        ResumeCreateDto resumeCreateDto = new ResumeCreateDto();
        resumeCreateDto.setApplicantId(currentUser.getId());
        resumeCreateDto.setName(resumeCreateWebDto.getName());
        resumeCreateDto.setCategoryId(resumeCreateWebDto.getCategoryId());
        resumeCreateDto.setSalary(resumeCreateWebDto.getSalary());
        resumeCreateDto.setIsActive(true);

        resumeService.createResume(resumeCreateDto);

        return "redirect:/profile/resumes";
    }
}