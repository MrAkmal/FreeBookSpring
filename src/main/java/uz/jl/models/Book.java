package uz.jl.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class Book implements BaseGenericEntity {
    private UUID id;
    private String name;
    private String description;
    private String coverPath;
    private Uploads uploads;

    public Book() {
        this.id = UUID.randomUUID();
    }

    public Book(String name, String description) {
        this();
        this.name = name;
        this.description = description;
    }

    public String getIdAsString() {
        return this.id.toString();
    }
}
