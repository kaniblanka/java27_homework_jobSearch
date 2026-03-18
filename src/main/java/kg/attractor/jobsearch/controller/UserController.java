package kg.attractor.jobsearch.controller;

import kg.attractor.jobsearch.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    @PostMapping
    public HttpStatus createUser(@RequestBody User user) {
        return HttpStatus.OK;
    }

    @GetMapping("job-seeker/{id}")
    public HttpStatus getJobSeeker(@PathVariable Long id) {
        return HttpStatus.OK;
    }

    @GetMapping("employer/{id}")
    public HttpStatus getEmployer(@PathVariable Long id) {
        return HttpStatus.OK;
    }
}