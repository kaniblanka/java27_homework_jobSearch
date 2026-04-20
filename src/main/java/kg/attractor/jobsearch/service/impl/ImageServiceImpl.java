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

    private final Path rootLocation = Paths.get("data", "images")
            .toAbsolutePath()
            .normalize();

    @Override
    public String uploadAvatar(MultipartFile file) {
        try {
            log.info("=== Image upload started ===");
            log.info("Target folder: {}", rootLocation);

            if (file == null || file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new RuntimeException("Only image files are allowed");
            }

            Files.createDirectories(rootLocation);

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || originalFilename.isBlank()) {
                throw new RuntimeException("Invalid file name");
            }

            originalFilename = StringUtils.cleanPath(originalFilename);

            String extension = "";
            int dotIndex = originalFilename.lastIndexOf('.');
            if (dotIndex >= 0) {
                extension = originalFilename.substring(dotIndex);
            }

            String newFileName = UUID.randomUUID() + extension;

            Path destinationFile = rootLocation.resolve(newFileName).normalize();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            log.info("File saved successfully: {}", destinationFile);

            return newFileName;

        } catch (Exception e) {
            log.error("Failed to upload avatar", e);
            throw new RuntimeException("Failed to upload avatar: " + e.getMessage());
        }
    }

    @Override
    public Resource getAvatar(String fileName) {
        try {
            Path file = rootLocation.resolve(fileName).normalize();
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