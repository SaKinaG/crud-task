package com.crud.crudtask.repository;

import com.crud.crudtask.entity.Author;
import com.crud.crudtask.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Author author set author.fullName = :fullName where author.id = :id")
    void update(@Param("fullName") String fullName, @Param("id") Long id);

}
