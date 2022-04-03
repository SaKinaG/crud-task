package com.crud.crudtask.controller;

import com.crud.crudtask.entity.Book;
import com.crud.crudtask.model.BookRequest;
import com.crud.crudtask.service.AuthorService;
import com.crud.crudtask.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @GetMapping("/add-book")
    public ModelAndView getAddbookView() {
        ModelAndView modelAndView = new ModelAndView("add-book");
        modelAndView.addObject("authors", authorService.getAll());
        modelAndView.addObject("book", new BookRequest());
        return modelAndView;
    }

    @PostMapping("/add-book")
    public String addBook(BookRequest bookRequest) {
        bookService.addBook(bookRequest);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("update-book");
        Book book = bookService.findById(id);
        BookRequest bookRequest = BookRequest.builder()
                .authorId(book.getAuthor().getId())
                .description(book.getDescription())
                .build();
        modelAndView.addObject("book", bookRequest);
        modelAndView.addObject("bookId", book.getId());
        modelAndView.addObject("authors", authorService.getAll());
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editBook(BookRequest bookRequest, @PathVariable Long id) {
        bookService.editBook(bookRequest, id);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/index";
    }
}
