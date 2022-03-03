package uz.jl.dto.book;

import lombok.*;
import uz.jl.models.Uploads;

import java.util.UUID;

/**
 * @author Doston Bokhodirov, Mon 5:58 PM. 2/14/2022
 */
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class BookDto {
    private String name;
    private String description;
    private String coverPath;
    private Uploads uploads;
}
