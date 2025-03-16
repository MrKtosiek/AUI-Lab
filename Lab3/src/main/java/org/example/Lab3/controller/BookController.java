package org.example.Lab3.controller;

import org.example.Lab3.dto.*;
import org.example.Lab3.model.*;
import org.example.Lab3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController
{
    private final BookService bookService;
    private final BookGenreService bookGenreService;

    @Autowired
    public BookController(BookService bookService, BookGenreService bookGenreService)
    {
        this.bookService = bookService;
        this.bookGenreService = bookGenreService;
    }

    @PostMapping
    public ResponseEntity<BookReadDTO> createBook(@RequestBody BookCreateUpdateDTO bookDto)
    {
        BookGenre genre = bookGenreService.findById(bookDto.getGenreId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
        Book book = Book.builder()
                .name(bookDto.getName())
                .authorFirstName(bookDto.getAuthorFirstName())
                .authorLastName(bookDto.getAuthorLastName())
                .genre(genre)
                .popularity(bookDto.getPopularity())
                .build();
        Book savedBook = bookService.save(book);
        return new ResponseEntity<>(new BookReadDTO(savedBook), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookReadDTO> getBook(@PathVariable UUID id)
    {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        return new ResponseEntity<>(new BookReadDTO(book), HttpStatus.OK);
    }

    @GetMapping
    public List<BookCollectionDTO> getAllBooks()
    {
        return bookService.findAll().stream()
                .map(book -> new BookCollectionDTO(book.getId(), book.getName(), book.getAuthorLastName()))
                .collect(Collectors.toList());
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable UUID bookId, @RequestBody BookCreateUpdateDTO bookDto)
    {
        Optional<Book> existingBookOptional = bookService.findById(bookId);

        if (existingBookOptional.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found with ID: " + bookId);
        }

        Optional<BookGenre> genreOptional = bookGenreService.findById(bookDto.getGenreId());
        if (genreOptional.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genre not found with ID: " + bookDto.getGenreId());
        }

        // Update existing book details
        Book existingBook = existingBookOptional.get();
        existingBook.setName(bookDto.getName());
        existingBook.setAuthorFirstName(bookDto.getAuthorFirstName());
        existingBook.setAuthorLastName(bookDto.getAuthorLastName());
        existingBook.setPopularity(bookDto.getPopularity());
        existingBook.setGenre(genreOptional.get());

        // Save the updated book
        bookService.save(existingBook);

        return ResponseEntity.ok(new BookCreateUpdateDTO(existingBook)); // Return updated book as DTO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id)
    {
        if (bookService.existsById(id))
        {
            bookService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }
}
