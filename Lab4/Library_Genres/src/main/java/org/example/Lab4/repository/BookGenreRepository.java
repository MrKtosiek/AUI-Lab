package org.example.Lab4.repository;

import org.example.Lab4.model.BookGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookGenreRepository extends JpaRepository<BookGenre, UUID>
{
    List<BookGenre> findByBooksNotNull();
}
