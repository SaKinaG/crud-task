package com.crud.crudtask.service.impl;

import com.crud.crudtask.entity.Author;
import com.crud.crudtask.entity.Book;
import com.crud.crudtask.model.BookRequest;
import com.crud.crudtask.repository.AuthorRepository;
import com.crud.crudtask.repository.BookRepository;
import com.crud.crudtask.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void addBook(BookRequest bookRequest) {
        Author author = authorRepository.findById(bookRequest.getAuthorId()).orElse(null);
        Book book = Book.builder()
                .description(bookRequest.getDescription())
                .author(author)
                .build();
        bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void editBook(BookRequest bookRequest, Long id) {
        Author author = authorRepository.findById(bookRequest.getAuthorId()).orElse(null);
        bookRepository.update(bookRequest.getDescription(), id, author);
    }

}
