package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.VacancyDto;
import kg.attractor.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("vacancies")
@RequiredArgsConstructor
public class VacancyPageController {

    private final VacancyService vacancyService;

    @GetMapping
    public String getVacancies(Model model,
                               Pageable pageable,
                               @RequestParam(defaultValue = "date") String sort) {

        Page<VacancyDto> vacanciesPage = vacancyService.getAllActiveVacancies(pageable, sort);

        model.addAttribute("vacanciesPage", vacanciesPage);
        model.addAttribute("vacancies", vacanciesPage.getContent());
        model.addAttribute("sort", sort);

        return "vacancies/vacancies";
    }
}