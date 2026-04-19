package kg.attractor.jobsearch.service.impl;

import kg.attractor.jobsearch.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final Path rootLocation = Paths.get("data/images");

    @Override
    public String uploadAvatar(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            Files.createDirectories(rootLocation);

            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String extension = "";

            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex >= 0) {
                extension = originalFilename.substring(dotIndex);
            }

            String newFileName = UUID.randomUUID() + extension;

            Path destinationFile = rootLocation.resolve(newFileName).normalize().toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            log.info("Avatar uploaded successfully: {}", newFileName);
            return newFileName;
        } catch (Exception e) {
            log.error("Failed to upload avatar", e);
            throw new RuntimeException("Failed to upload avatar");
        }
    }

    @Override
    public Resource getAvatar(String fileName) {
        try {
            Path file = rootLocation.resolve(fileName).normalize().toAbsolutePath();
            Resource resource = new UrlResource(file.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("File not found: " + fileName);
            }

            return resource;
        } catch (MalformedURLException e) {
            log.error("Failed to read avatar: {}", fileName, e);
            throw new RuntimeException("Failed to read avatar");
        }
    }
}