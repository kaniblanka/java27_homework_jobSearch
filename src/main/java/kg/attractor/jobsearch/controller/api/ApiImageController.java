package kg.attractor.jobsearch.controller.api;

import kg.attractor.jobsearch.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/images")
@RequiredArgsConstructor
public class ApiImageController {

    private final ImageService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String fileName = imageService.uploadAvatar(file);
        return ResponseEntity.ok(fileName);
    }

    @GetMapping("{fileName}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String fileName) {
        Resource resource = imageService.getAvatar(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
                .body(resource);
    }
}