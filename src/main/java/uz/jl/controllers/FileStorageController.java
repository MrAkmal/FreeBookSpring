package uz.jl.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.jl.models.Uploads;
import uz.jl.services.FileStorageService;

@Controller
@RequestMapping("/uploads/*")
public class FileStorageController {

    final FileStorageService fileStorageService;

    public FileStorageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("{filename:.+}")
    public ResponseEntity<?> download(@PathVariable(name = "filename") String fileName) {
        Uploads loadedResource = fileStorageService.loadResource(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loadedResource.getOriginalName() + "\"")
                .body(loadedResource.getResource());
    }
}
