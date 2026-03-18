package kg.attractor.jobsearch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    @PostMapping
    public HttpStatus uploadImage(@RequestBody String imageName) {
        return HttpStatus.OK;
    }

    @GetMapping("{fileName}")
    public HttpStatus downloadImage(@PathVariable String fileName) {
        return HttpStatus.OK;
    }
}