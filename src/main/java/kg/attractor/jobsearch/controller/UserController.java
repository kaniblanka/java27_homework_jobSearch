package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.model.User;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("email")
    public User findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("exists")
    public boolean existsByEmail(@RequestParam String email) {
        return userService.existsByEmail(email);
    }
}