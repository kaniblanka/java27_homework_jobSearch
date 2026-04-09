package kg.attractor.jobsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("profile")
@RequiredArgsConstructor
public class ProfileController {

    @GetMapping
    public String profilePage() {
        return "profile/cabinet";
    }

    @GetMapping("edit")
    public String editProfilePage() {
        return "profile/edit";
    }

    @GetMapping("vacancies")
    public String myVacanciesPage() {
        return "vacancies/my-vacancies";
    }
}