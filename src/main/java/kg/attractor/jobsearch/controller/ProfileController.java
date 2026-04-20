package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.UserNotFoundException;
import kg.attractor.jobsearch.service.ImageService;
import kg.attractor.jobsearch.service.ResumeService;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ResumeService resumeService;
    private final UserService userService;
    private final VacancyService vacancyService;
    private final ImageService imageService;

    @GetMapping
    public String profilePage(Model model, Principal principal) throws UserNotFoundException {
        UserDto user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "profile/cabinet";
    }

    @GetMapping("edit")
    public String editProfilePage(Model model, Principal principal) throws UserNotFoundException {
        UserDto user = userService.findByEmail(principal.getName());

        UserEditDto userEditDto = new UserEditDto();
        userEditDto.setName(user.getName());
        userEditDto.setSurname(user.getSurname());
        userEditDto.setAge(user.getAge());
        userEditDto.setEmail(user.getEmail());
        userEditDto.setPhoneNumber(user.getPhoneNumber());
        userEditDto.setAvatar(user.getAvatar());

        model.addAttribute("userEditDto", userEditDto);
        return "profile/edit";
    }

    @PostMapping("edit")
    public String updateProfile(@Valid @ModelAttribute UserEditDto userEditDto,
                                BindingResult bindingResult,
                                Principal principal,
                                Model model) throws UserNotFoundException, UpdateEntryException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userEditDto", userEditDto);
            return "profile/edit";
        }

        UserDto currentUser = userService.findByEmail(principal.getName());
        userService.updateProfile(currentUser.getId(), userEditDto);

        return "redirect:/profile";
    }

    @PostMapping("avatar")
    public String uploadAvatar(@RequestParam("avatarFile") MultipartFile avatarFile,
                               Principal principal,
                               Model model) throws UserNotFoundException, UpdateEntryException {
        UserDto currentUser = userService.findByEmail(principal.getName());

        if (avatarFile == null || avatarFile.isEmpty()) {
            UserEditDto userEditDto = new UserEditDto();
            userEditDto.setName(currentUser.getName());
            userEditDto.setSurname(currentUser.getSurname());
            userEditDto.setAge(currentUser.getAge());
            userEditDto.setEmail(currentUser.getEmail());
            userEditDto.setPhoneNumber(currentUser.getPhoneNumber());
            userEditDto.setAvatar(currentUser.getAvatar());

            model.addAttribute("userEditDto", userEditDto);
            model.addAttribute("avatarError", "Сначала выберите файл");
            return "profile/edit";
        }

        String fileName = imageService.uploadAvatar(avatarFile);

        UserEditDto userEditDto = new UserEditDto();
        userEditDto.setName(currentUser.getName());
        userEditDto.setSurname(currentUser.getSurname());
        userEditDto.setAge(currentUser.getAge());
        userEditDto.setEmail(currentUser.getEmail());
        userEditDto.setPhoneNumber(currentUser.getPhoneNumber());
        userEditDto.setAvatar(fileName);

        userService.updateProfile(currentUser.getId(), userEditDto);

        return "redirect:/profile";
    }

    @GetMapping("vacancies")
    public String myVacanciesPage(Model model, Principal principal) throws UserNotFoundException {
        UserDto user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("vacancies", vacancyService.getVacanciesByAuthorId(user.getId()));
        return "vacancies/my-vacancies";
    }

    @GetMapping("resumes")
    public String myResumesPage(Model model, Principal principal) throws UserNotFoundException {
        UserDto user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("resumes", resumeService.getResumesByApplicantId(user.getId()));
        return "resumes/my-resumes";
    }
}