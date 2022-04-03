package com.crud.crudtask.service.impl;

import com.crud.crudtask.entity.Author;
import com.crud.crudtask.entity.Book;
import com.crud.crudtask.model.AuthorRequest;
import com.crud.crudtask.model.EditAuthorRequest;
import com.crud.crudtask.repository.AuthorRepository;
import com.crud.crudtask.repository.BookRepository;
import com.crud.crudtask.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public void addAuthor(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setFullName(authorRequest.getFullName());
        if (authorRequest.getBookDescriptionsByComma() != null && !authorRequest.getBookDescriptionsByComma().isEmpty()) {
            List<Book> books = Arrays.stream(authorRequest.getBookDescriptionsByComma().split(","))
                    .map(description ->
                            Book.builder()
                                    .description(description)
                                    .author(author)
                                    .build()
                    )
                    .collect(Collectors.toList());
            author.setBooks(books);
        }
        authorRepository.save(author);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void editAuthor(EditAuthorRequest editAuthorRequest, Long id) {
        authorRepository.update(editAuthorRequest.getFullName(), id);
    }

}
