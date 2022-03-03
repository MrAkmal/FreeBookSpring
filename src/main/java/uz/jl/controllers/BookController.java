package uz.jl.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.jl.dto.book.BookCreateDto;
import uz.jl.dto.book.BookDto;
import uz.jl.dto.book.BookUpdateDto;
import uz.jl.models.Book;
import uz.jl.services.BookService;

import javax.servlet.annotation.MultipartConfig;


@Controller
@MultipartConfig
@RequestMapping("/book/*")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(@Qualifier("fakeBookService") BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "create/")
    public String createPage(@ModelAttribute BookCreateDto dto) {
        bookService.create(dto);
        return "redirect:/book/list/";
    }

    @RequestMapping(value = "update/{book_id}", method = RequestMethod.GET)
    public String updatePage(Model model, @PathVariable(name = "book_id") String id) {
        BookDto bookDto = bookService.get(id);
        model.addAttribute("book", bookDto);
        return "book/update";
    }

    @RequestMapping(value = "update/", method = RequestMethod.POST)
    public String update(@ModelAttribute BookUpdateDto dto) {
        bookService.update(dto);
        return "redirect:/book/list/";
    }

//    @RequestMapping(value = "detail/{book_id}", method = RequestMethod.GET)
//    public String getPage(Model model, @PathVariable(name = "book_id") String id) {
//        Book book = bookService.get(id);
//        model.addAttribute("book", book);
//        return "book/detail";
//    }

    @RequestMapping(value = "delete/{book_id}", method = RequestMethod.GET)
    public String deletePage(Model model, @PathVariable(name = "book_id") String id) {
        model.addAttribute("book", bookService.get(id));
        return "book/delete";
    }


    @RequestMapping(value = "delete/{book_id}", method = RequestMethod.POST)
    public String delete(@PathVariable(name = "book_id") String id) {
        bookService.delete(id);
        return "redirect:/book/list/";
    }

    @RequestMapping(value = "list/", method = RequestMethod.GET)
    public String listPage(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "book/list";
    }
}