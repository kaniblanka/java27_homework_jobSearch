package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.UserNotFoundException;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("employer/vacancies")
@RequiredArgsConstructor
public class EmployerVacancyController {

    private final VacancyService vacancyService;
    private final UserService userService;

    @GetMapping("create")
    public String createVacancyPage(Model model) {
        VacancyDto vacancyDto = new VacancyDto();
        vacancyDto.setIsActive(true);

        model.addAttribute("vacancyDto", vacancyDto);
        return "vacancies/create";
    }

    @PostMapping("create")
    public String createVacancy(@Valid VacancyDto vacancyDto,
                                BindingResult bindingResult,
                                Principal principal,
                                Model model) throws UserNotFoundException, CreateEntryException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("vacancyDto", vacancyDto);
            return "vacancies/create";
        }

        UserDto currentUser = userService.findByEmail(principal.getName());

        vacancyDto.setAuthorId(currentUser.getId());
        vacancyDto.setIsActive(true);
        vacancyDto.setCreatedDate(LocalDateTime.now());
        vacancyDto.setUpdateTime(LocalDateTime.now());

        vacancyService.createVacancy(vacancyDto);

        return "redirect:/profile/vacancies";
    }
}