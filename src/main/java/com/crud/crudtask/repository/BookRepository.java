package com.crud.crudtask.repository;

import com.crud.crudtask.entity.Author;
import com.crud.crudtask.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Book book set book.description = :description, book.author = :author where book.id = :id")
    void update(@Param("description") String description, @Param("id") Long id, @Param("author") Author author);

}
