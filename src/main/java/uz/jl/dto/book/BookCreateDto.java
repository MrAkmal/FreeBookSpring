package uz.jl.dto.book;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDto {
    private String name;
    private String description;
    private String coverPath;

    private MultipartFile file;
    private MultipartFile image;

}
