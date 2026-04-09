package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.service.UserService;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;
    private final VacancyService vacancyService;

    @GetMapping("profile")
    public String profile(Model model) {
        return "profile/cabinet";
    }

    @GetMapping("profile/edit")
    public String editProfile(Model model) {
        return "profile/edit";
    }

    @GetMapping("profile/vacancies")
    public String myVacancies(Model model) {
        return "vacancies/my-vacancies";
    }
}