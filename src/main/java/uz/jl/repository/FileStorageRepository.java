package uz.jl.repository;

import org.springframework.stereotype.Repository;
import uz.jl.models.Uploads;

import java.util.List;

/**
 * @author Doston Bokhodirov, Mon 10:02 PM. 2/14/2022
 */
@Repository("fileStorageRepository")
public class FileStorageRepository extends AbstractRepository<Uploads> {

    public FileStorageRepository(List<Uploads> list) {
        super(list);
    }

    public Uploads save(Uploads file) {
        list.add(file);
        return file;
    }

    public Uploads load(String fileName) {
        return list.stream().filter(file -> file.getGeneratedName().equals(fileName)).findFirst().orElseThrow(() -> {
            throw new RuntimeException("File Not Found");
        });
    }
}
