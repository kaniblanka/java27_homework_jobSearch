package kg.attractor.jobsearch.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch.dto.UserCreateDto;
import kg.attractor.jobsearch.dto.UserDto;
import kg.attractor.jobsearch.dto.UserEditDto;
import kg.attractor.jobsearch.exception.CreateEntryException;
import kg.attractor.jobsearch.exception.UpdateEntryException;
import kg.attractor.jobsearch.exception.UserNotFoundException;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserCreateDto userCreateDto) throws CreateEntryException {
        return userService.createUser(userCreateDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserDto findById(@PathVariable Long id) throws UserNotFoundException {
        return userService.findById(id);
    }

    @GetMapping("email")
    public UserDto findByEmail(@RequestParam String email) throws UserNotFoundException {
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
    public UserDto findByPhone(@RequestParam String phone) throws UserNotFoundException {
        return userService.findByPhone(phone);
    }

    @PutMapping("{id}")
    public HttpStatus updateProfile(@PathVariable Long id,
                                    @Valid @RequestBody UserEditDto userEditDto) throws UserNotFoundException, UpdateEntryException {
        userService.updateProfile(id, userEditDto);
        return HttpStatus.OK;
    }
}