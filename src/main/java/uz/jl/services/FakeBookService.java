package uz.jl.services;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.jl.dto.book.BookCreateDto;
import uz.jl.dto.book.BookDto;
import uz.jl.dto.book.BookUpdateDto;
import uz.jl.mappers.BookMapper;
import uz.jl.models.Book;
import uz.jl.models.Uploads;
import uz.jl.repository.BookRepository;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import static uz.jl.services.FileStorageService.UNICORN_UPLOADS_B_4_IMG;
import static uz.jl.services.FileStorageService.UNICORN_UPLOADS_B_4_LIB;


@Slf4j
@Service("fakeBookService")
public class FakeBookService implements BookService {

    private final BookMapper mapper;
    private final FileStorageService fileStorageService;
    private final BookRepository repository;


    @Autowired
    public FakeBookService(
            BookMapper mapper,
            @Qualifier("fileStorageService") FileStorageService fileStorageService,
            @Qualifier("bookRepository") BookRepository bookRepository) {
        this.mapper = mapper;
        this.fileStorageService = fileStorageService;
        this.repository = bookRepository;
    }


    @Override
    public List<BookDto> getAll() {
        List<Book> bookList = repository.getAll();
        return mapper.toDto(bookList);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public BookDto get(String id) {
        Book book = repository.get(id);
        return mapper.toDto(book);
    }

    @Override
    public boolean update(BookUpdateDto dto) {
        Book book = mapper.fromUpdateDto(dto);
        repository.update(book);
        return true;
    }

    @Override
    public UUID create(BookCreateDto dto) {
        return create(dto, dto.getFile(), dto.getImage());
    }

    @Override
    @SneakyThrows
    public UUID create(final BookCreateDto dto, @NonNull MultipartFile file, @NonNull MultipartFile image) {
        Book book = mapper.fromCreateDto(dto);
        Uploads uploads = fileStorageService.store(file);
        String store = store(image);
        book.setUploads(uploads);
        book.setCoverPath("/main/" + store);
        return repository.create(book);
    }

    @SneakyThrows
    private String store(MultipartFile image) {
        String extension = StringUtils.getFilenameExtension(image.getOriginalFilename());
        String generatedName = "%s.%s".formatted(System.currentTimeMillis(), extension);
        Path rootPath = Paths.get(UNICORN_UPLOADS_B_4_IMG, generatedName);
        Files.copy(image.getInputStream(), rootPath, StandardCopyOption.REPLACE_EXISTING);
        return generatedName;
    }


}
