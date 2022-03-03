package uz.jl.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.core.io.Resource;

import java.util.UUID;

@Data
@Builder
public class Uploads implements BaseGenericEntity {
    private UUID id;
    private String originalName;
    private String generatedName;
    private String contentType;
    private long size;
    private String path;
    private Resource resource;
}
