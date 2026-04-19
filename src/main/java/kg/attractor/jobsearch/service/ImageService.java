package kg.attractor.jobsearch.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String uploadAvatar(MultipartFile file);

    Resource getAvatar(String fileName);
}