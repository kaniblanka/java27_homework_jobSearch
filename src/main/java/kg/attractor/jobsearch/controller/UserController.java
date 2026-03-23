package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.UserDto;
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
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("email")
    public UserDto findByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("exists")
    public boolean existsByEmail(@RequestParam String email) {
        return userService.existsByEmail(email);
    }

    @GetMapping("by-name")
    public List<UserDto> findByName(@RequestParam String name) {
        return userService.findByName(name);
    }

    @GetMapping("by-phone")
    public UserDto findByPhone(@RequestParam String phone) {
        return userService.findByPhone(phone);
    }
}