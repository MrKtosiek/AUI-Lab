package org.example.Lab3.service;

import org.example.Lab3.model.BookGenre;
import org.example.Lab3.repository.BookGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookGenreService
{
    private final BookGenreRepository bookGenreRepository;

    @Autowired
    public BookGenreService(BookGenreRepository bookGenreRepository)
    {
        this.bookGenreRepository = bookGenreRepository;
    }

    public List<BookGenre> findAll()
    {
        return bookGenreRepository.findAll();
    }

    public Optional<BookGenre> findById(UUID id)
    {
        return bookGenreRepository.findById(id);
    }

    public BookGenre save(BookGenre bookGenre)
    {
        return bookGenreRepository.save(bookGenre);
    }

    public boolean existsById(UUID id)
    {
        return bookGenreRepository.existsById(id);
    }

    public void deleteById(UUID id)
    {
        bookGenreRepository.deleteById(id);
    }
}
