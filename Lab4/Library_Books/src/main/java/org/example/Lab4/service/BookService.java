package org.example.Lab4.service;

import org.example.Lab4.model.Book;
import org.example.Lab4.model.SimplifiedBookGenre;
import org.example.Lab4.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService
{
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }

    public List<Book> findByBookGenre(SimplifiedBookGenre bookGenre)
    {
        return bookRepository.findByGenre(bookGenre);
    }

    public List<Book> findAll()
    {
        return bookRepository.findAll();
    }

    public boolean existsById(UUID id)
    {
        return bookRepository.existsById(id);
    }

    public void deleteById(UUID id)
    {
        bookRepository.deleteById(id);
    }

    public Book save(Book book)
    {
        return bookRepository.save(book);
    }

    public Optional<Book> findById(UUID id)
    {
        return bookRepository.findById(id);
    }
}
