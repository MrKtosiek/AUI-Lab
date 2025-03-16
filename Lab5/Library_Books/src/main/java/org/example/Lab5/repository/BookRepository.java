package org.example.Lab5.repository;

import org.example.Lab5.model.Book;
import org.example.Lab5.model.SimplifiedBookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>
{
    List<Book> findByGenre(SimplifiedBookGenre bookGenre);
}
