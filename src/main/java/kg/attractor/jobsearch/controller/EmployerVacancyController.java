package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.VacancyCreateWebDto;
import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.UserNotFoundException;
import kg.attractor.jobsearch.exception.VacancyNotFoundException;
import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("vacancyCreateWebDto", new VacancyCreateWebDto());
        return "vacancies/create-vacancy";
    }

    @PostMapping("create")
    public String createVacancy(@Valid @ModelAttribute VacancyCreateWebDto vacancyCreateWebDto,
                                BindingResult bindingResult,
                                Principal principal,
                                Model model) throws UserNotFoundException, CreateEntryException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("vacancyCreateWebDto", vacancyCreateWebDto);
            return "vacancies/create-vacancy";
        }

        if (vacancyCreateWebDto.getExpTo() < vacancyCreateWebDto.getExpFrom()) {
            model.addAttribute("vacancyCreateWebDto", vacancyCreateWebDto);
            model.addAttribute("expError", "Опыт до не может быть меньше, чем опыт от");
            return "vacancies/create-vacancy";
        }

        UserDto currentUser = userService.findByEmail(principal.getName());

        VacancyDto vacancyDto = new VacancyDto();
        vacancyDto.setName(vacancyCreateWebDto.getName());
        vacancyDto.setDescription(vacancyCreateWebDto.getDescription());
        vacancyDto.setCategoryId(vacancyCreateWebDto.getCategoryId());
        vacancyDto.setSalary(vacancyCreateWebDto.getSalary());
        vacancyDto.setExpFrom(vacancyCreateWebDto.getExpFrom());
        vacancyDto.setExpTo(vacancyCreateWebDto.getExpTo());
        vacancyDto.setAuthorId(currentUser.getId());
        vacancyDto.setIsActive(true);
        vacancyDto.setCreatedDate(LocalDateTime.now());
        vacancyDto.setUpdateTime(LocalDateTime.now());

        vacancyService.createVacancy(vacancyDto);

        return "redirect:/profile/vacancies";
    }

    @GetMapping("edit/{id}")
    public String editVacancyPage(@PathVariable Long id,
                                  Principal principal,
                                  Model model) throws VacancyNotFoundException, UserNotFoundException {
        VacancyDto vacancy = vacancyService.getVacancyById(id);
        UserDto currentUser = userService.findByEmail(principal.getName());

        if (!vacancy.getAuthorId().equals(currentUser.getId())) {
            throw new VacancyNotFoundException();
        }

        VacancyCreateWebDto dto = new VacancyCreateWebDto();
        dto.setName(vacancy.getName());
        dto.setDescription(vacancy.getDescription());
        dto.setCategoryId(vacancy.getCategoryId());
        dto.setSalary(vacancy.getSalary());
        dto.setExpFrom(vacancy.getExpFrom());
        dto.setExpTo(vacancy.getExpTo());

        model.addAttribute("vacancyId", id);
        model.addAttribute("vacancyCreateWebDto", dto);

        return "vacancies/edit-vacancy";
    }

    @PostMapping("edit/{id}")
    public String updateVacancy(@PathVariable Long id,
                                @Valid @ModelAttribute VacancyCreateWebDto vacancyCreateWebDto,
                                BindingResult bindingResult,
                                Principal principal,
                                Model model) throws VacancyNotFoundException, UserNotFoundException, UpdateEntryException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("vacancyId", id);
            model.addAttribute("vacancyCreateWebDto", vacancyCreateWebDto);
            return "vacancies/edit-vacancy";
        }

        if (vacancyCreateWebDto.getExpTo() < vacancyCreateWebDto.getExpFrom()) {
            model.addAttribute("vacancyId", id);
            model.addAttribute("vacancyCreateWebDto", vacancyCreateWebDto);
            model.addAttribute("expError", "Опыт до не может быть меньше, чем опыт от");
            return "vacancies/edit-vacancy";
        }

        VacancyDto existingVacancy = vacancyService.getVacancyById(id);
        UserDto currentUser = userService.findByEmail(principal.getName());

        if (!existingVacancy.getAuthorId().equals(currentUser.getId())) {
            throw new VacancyNotFoundException();
        }

        VacancyDto vacancyDto = new VacancyDto();
        vacancyDto.setId(id);
        vacancyDto.setName(vacancyCreateWebDto.getName());
        vacancyDto.setDescription(vacancyCreateWebDto.getDescription());
        vacancyDto.setCategoryId(vacancyCreateWebDto.getCategoryId());
        vacancyDto.setSalary(vacancyCreateWebDto.getSalary());
        vacancyDto.setExpFrom(vacancyCreateWebDto.getExpFrom());
        vacancyDto.setExpTo(vacancyCreateWebDto.getExpTo());
        vacancyDto.setAuthorId(currentUser.getId());
        vacancyDto.setIsActive(existingVacancy.getIsActive());
        vacancyDto.setCreatedDate(existingVacancy.getCreatedDate());
        vacancyDto.setUpdateTime(LocalDateTime.now());

        vacancyService.updateVacancy(id, vacancyDto);

        return "redirect:/profile/vacancies";
    }
}