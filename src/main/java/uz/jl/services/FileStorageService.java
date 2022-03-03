package uz.jl.services;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.jl.models.Uploads;
import uz.jl.repository.FileStorageRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Service("fileStorageService")
public class FileStorageService {

    public static final String UNICORN_UPLOADS_B_4_LIB = "/unicorn/uploads/b4/lib/";
    public static final Path PATH_LIB = Paths.get(UNICORN_UPLOADS_B_4_LIB);

    public static final String UNICORN_UPLOADS_B_4_IMG = "/unicorn/uploads/b4/lib/images/";
    public static final Path PATH_IMG = Paths.get(UNICORN_UPLOADS_B_4_IMG);
    private final FileStorageRepository fileStorageRepository;

    @Autowired
    public FileStorageService(@Qualifier("fileStorageRepository") FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
    }

    @PostConstruct
    public void init() {
        if (!Files.exists(PATH_LIB)) {
            try {
                Files.createDirectories(PATH_LIB);
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
            }
        }

        if (!Files.exists(PATH_IMG)) {
            try {
                Files.createDirectories(PATH_IMG);
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
            }
        }

    }

    @SneakyThrows
    public Uploads store(@NonNull MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String generatedName = "%s.%s".formatted(System.currentTimeMillis(), extension);
        Path rootPath = Paths.get(UNICORN_UPLOADS_B_4_LIB, generatedName);
        Files.copy(file.getInputStream(), rootPath, StandardCopyOption.REPLACE_EXISTING);
        Uploads uploadedFile = Uploads
                .builder()
                .originalName(originalFilename)
                .generatedName(generatedName)
                .contentType(file.getContentType())
                .path("/uploads/" + generatedName)
                .size(file.getSize())
                .build();
        return fileStorageRepository.save(uploadedFile);
    }

    public Uploads storeAsync(@NonNull MultipartFile file) {
        // TODO: 2/14/2022 need to write logic to store file asynchronously
        return null;
    }


    public Uploads loadResource(@NonNull String fileName) {
        Uploads uploads = fileStorageRepository.load(fileName);
        FileSystemResource resource = new FileSystemResource(UNICORN_UPLOADS_B_4_LIB + fileName);
        uploads.setResource(resource);
        return uploads;
    }
}
