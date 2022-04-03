package com.crud.crudtask.service;

import com.crud.crudtask.entity.Book;
import com.crud.crudtask.model.BookRequest;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    void delete(Long id);

    void addBook(BookRequest bookRequest);

    Book findById(Long id);

    void editBook(BookRequest bookRequest, Long id);
}
