package uz.jl.services;

import org.springframework.web.multipart.MultipartFile;
import uz.jl.dto.book.BookCreateDto;
import uz.jl.dto.book.BookDto;
import uz.jl.dto.book.BookUpdateDto;
import uz.jl.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface BookService {

    List<BookDto> getAll();

    void delete(String id);

    BookDto get(String id);

    boolean update(BookUpdateDto dto);

    UUID create(BookCreateDto dto);

    UUID create(BookCreateDto dto, MultipartFile file, MultipartFile image);
}
