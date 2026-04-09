package kg.attractor.jobsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    @GetMapping("register")
    public String registerPage() {
        return "auth/register";
    }

    @GetMapping("login")
    public String loginPage() {
        return "auth/login";
    }
}