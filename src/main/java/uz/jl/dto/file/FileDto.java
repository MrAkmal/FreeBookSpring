package uz.jl.dto.file;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    private String originalName;
    private String newName;
    private long size;
    private String url;
    private String contentType;
    private Resource resource;
}
