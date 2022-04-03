package com.crud.crudtask.controller;

import com.crud.crudtask.model.AuthorRequest;
import com.crud.crudtask.service.AuthorService;
import com.crud.crudtask.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index")
public class MainController {

    private final BookService bookService;
    private final AuthorService authorService;

    public MainController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("books", bookService.getAll());
        modelAndView.addObject("authors", authorService.getAll());
        return modelAndView;
    }

}
