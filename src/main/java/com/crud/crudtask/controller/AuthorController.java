package com.crud.crudtask.controller;

import com.crud.crudtask.model.AuthorRequest;
import com.crud.crudtask.model.EditAuthorRequest;
import com.crud.crudtask.service.AuthorService;
import com.crud.crudtask.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/add-author")
    public ModelAndView getAddAuthorView() {
        ModelAndView modelAndView = new ModelAndView("add-author");
        modelAndView.addObject("author", new AuthorRequest());
        return modelAndView;
    }

    @PostMapping("/add-author")
    public String addAuthor(AuthorRequest authorRequest) {
        authorService.addAuthor(authorRequest);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("update-author");
        modelAndView.addObject("author", authorService.findById(id));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editAuthor(EditAuthorRequest editAuthorRequest, @PathVariable Long id) {
        authorService.editAuthor(editAuthorRequest, id);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/index";
    }
}
