package uz.jl.mappers;

import org.springframework.stereotype.Component;
import uz.jl.dto.book.BookCreateDto;
import uz.jl.dto.book.BookDto;
import uz.jl.dto.book.BookUpdateDto;
import uz.jl.models.Book;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class BookMapper {

    public Book fromCreateDto(BookCreateDto dto) {
        return new Book(dto.getName(), dto.getDescription());
    }

    public Book fromUpdateDto(BookUpdateDto dto) {
        return Book.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .coverPath(dto.getCoverPath())
                .uploads(dto.getUploads())
                .build();
    }

    public BookDto toDto(Book book) {
        return BookDto.builder()
                .name(book.getName())
                .description(book.getDescription())
                .coverPath(book.getCoverPath())
                .uploads(book.getUploads())
                .build();
    }

    public List<BookDto> toDto(List<Book> bookList) {
        return bookList.stream().map(this::toDto).collect(Collectors.toList());
    }

}
