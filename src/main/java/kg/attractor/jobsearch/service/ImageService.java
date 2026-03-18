package kg.attractor.jobsearch.service;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

    public String uploadAvatar(String imageName) {
        // тут загружаем аватар пользователя
        // пока просто принимаем строку (например имя файла)
        // потом это будет файл
        return imageName;
    }

    public String getAvatar(String fileName) {
        // получаем аватар по имени файла
        // по идее читаем из папки data/images
        return fileName;
    }
}