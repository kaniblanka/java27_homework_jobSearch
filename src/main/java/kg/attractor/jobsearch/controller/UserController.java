package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PutMapping("{id}")
    public HttpStatus updateProfile(@PathVariable Long id,
                                    @RequestBody UserEditDto userEditDto) {
        return userService.updateProfile(id, userEditDto)
                ? HttpStatus.OK
                : HttpStatus.NOT_FOUND;
    }
}