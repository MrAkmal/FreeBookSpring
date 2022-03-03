package uz.jl.dto.book;

import lombok.Data;
import uz.jl.models.Uploads;

import java.util.UUID;

@Data
public class BookUpdateDto {
    private UUID id;
    private String name;
    private String description;
    private String coverPath;
    private Uploads uploads;
}
