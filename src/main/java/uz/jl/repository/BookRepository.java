package uz.jl.repository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import uz.jl.exceptions.NotFoundException;
import uz.jl.models.Book;

import java.util.List;
import java.util.UUID;

/**
 * @author Doston Bokhodirov, Mon 9:58 PM. 2/14/2022
 */
@Repository("bookRepository")
public class BookRepository extends AbstractRepository<Book> {

    public BookRepository(List<Book> bookList) {
        super(bookList);
    }

    public List<Book> getAll() {
        return list;
    }

    public void delete(String id) {
        list.removeIf(book -> book.getIdAsString().equals(id));
    }

    public Book get(String id) {
        return list.stream().filter(book -> book.getIdAsString().equals(id)).findFirst().orElseThrow(() -> new NotFoundException("Book Not Found with id", HttpStatus.NOT_FOUND));
    }

    public UUID create(Book book) {
        list.add(book);
        return book.getId();
    }

    public void update(Book entity) {
        for (Book book : list) {
            if (book.getId().equals(entity.getId())) {
                book.setName(entity.getName());
                book.setDescription(entity.getDescription());
                book.setCoverPath(entity.getCoverPath());
                book.setUploads(entity.getUploads());
                break;
            }
        }
    }
}
