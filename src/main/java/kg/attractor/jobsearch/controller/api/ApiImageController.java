package kg.attractor.jobsearch.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/images")
@RequiredArgsConstructor
public class ApiImageController {

    @PostMapping
    public HttpStatus uploadImage(@RequestBody String imageName) {
        return HttpStatus.OK;
    }

    @GetMapping("{fileName}")
    public HttpStatus downloadImage(@PathVariable String fileName) {
        return HttpStatus.OK;
    }
}