package com.crud.crudtask.service;

import com.crud.crudtask.entity.Author;
import com.crud.crudtask.entity.Book;
import com.crud.crudtask.model.AuthorRequest;
import com.crud.crudtask.model.EditAuthorRequest;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    void addAuthor(AuthorRequest authorRequest);

    void delete(Long id);

    Author findById(Long id);

    void editAuthor(EditAuthorRequest editAuthorRequest, Long id);
}
