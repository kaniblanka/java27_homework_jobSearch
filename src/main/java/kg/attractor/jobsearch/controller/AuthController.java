package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserCreateDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("register")
    public String registerPage(Model model) {
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "auth/register";
    }

    @PostMapping("register")
    public String register(@Valid @ModelAttribute UserCreateDto userCreateDto,
                           BindingResult bindingResult,
                           Model model) throws CreateEntryException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userCreateDto", userCreateDto);
            model.addAttribute("bindingResult", bindingResult);
            return "auth/register";
        }

        userService.createUser(userCreateDto);
        return "redirect:/login";
    }

    @GetMapping("login")
    public String loginPage() {
        return "auth/login";
    }
}