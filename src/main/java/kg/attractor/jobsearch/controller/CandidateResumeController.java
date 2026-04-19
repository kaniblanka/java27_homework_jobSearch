package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.ResumeCreateDto;
import kg.attractor.jobsearch.dto.ResumeCreateWebDto;
import kg.attractor.jobsearch.dto.ResumeDto;
import kg.attractor.jobsearch.dto.ResumeEditWebDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.ResumeNotFoundException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
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
                               Model model) throws UserNotFoundException, CreateEntryException {
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

    @GetMapping("edit/{id}")
    public String editPage(@PathVariable Long id,
                           Principal principal,
                           Model model) throws ResumeNotFoundException, UserNotFoundException {
        ResumeDto resume = resumeService.getResumeById(id);
        UserDto user = userService.findByEmail(principal.getName());

        if (!resume.getApplicantId().equals(user.getId())) {
            throw new ResumeNotFoundException();
        }

        ResumeEditWebDto dto = new ResumeEditWebDto();
        dto.setName(resume.getName());
        dto.setCategoryId(resume.getCategoryId());
        dto.setSalary(resume.getSalary());

        model.addAttribute("resumeId", id);
        model.addAttribute("resumeEditWebDto", dto);

        return "resumes/edit-resume";
    }

    @PostMapping("edit/{id}")
    public String updateResume(@PathVariable Long id,
                               @Valid @ModelAttribute ResumeEditWebDto dto,
                               BindingResult bindingResult,
                               Principal principal,
                               Model model) throws ResumeNotFoundException, UserNotFoundException, UpdateEntryException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("resumeId", id);
            model.addAttribute("resumeEditWebDto", dto);
            return "resumes/edit-resume";
        }

        ResumeDto existingResume = resumeService.getResumeById(id);
        UserDto user = userService.findByEmail(principal.getName());

        if (!existingResume.getApplicantId().equals(user.getId())) {
            throw new ResumeNotFoundException();
        }

        ResumeCreateDto resumeToUpdate = new ResumeCreateDto();
        resumeToUpdate.setApplicantId(user.getId());
        resumeToUpdate.setName(dto.getName());
        resumeToUpdate.setCategoryId(dto.getCategoryId());
        resumeToUpdate.setSalary(dto.getSalary());
        resumeToUpdate.setIsActive(existingResume.getIsActive());

        resumeService.updateResume(id, resumeToUpdate);

        return "redirect:/profile/resumes";
    }
}