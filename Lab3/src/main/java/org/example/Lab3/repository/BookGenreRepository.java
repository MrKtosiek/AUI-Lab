package org.example.Lab3.repository;

import org.example.Lab3.model.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, UUID>
{
    List<BookGenre> findByBooksNotNull();
}
