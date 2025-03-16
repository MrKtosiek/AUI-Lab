package org.example.Lab5.controller;

import org.example.Lab5.dto.BookGenreCollectionDTO;
import org.example.Lab5.dto.BookGenreCreateUpdateDTO;
import org.example.Lab5.dto.BookGenreReadDTO;
import org.example.Lab5.model.BookGenre;
import org.example.Lab5.service.BookGenreService;
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
@RequestMapping("/api/genres")
public class BookGenreController
{

    private final BookGenreService bookGenreService;

    @Autowired
    public BookGenreController(BookGenreService bookGenreService)
    {
        this.bookGenreService = bookGenreService;
    }

    @PostMapping
    public ResponseEntity<BookGenreReadDTO> createGenre(@RequestBody BookGenreCreateUpdateDTO genreDto)
    {
        System.out.println("Creating genre: " + genreDto.toString());
        BookGenre genre = BookGenre.builder()
                .name(genreDto.getName())
                .popularity(genreDto.getPopularity())
                .build();
        BookGenre savedGenre = bookGenreService.save(genre);
        return new ResponseEntity<>(new BookGenreReadDTO(savedGenre), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookGenreReadDTO> getGenre(@PathVariable UUID id)
    {
        BookGenre genre = bookGenreService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
        return new ResponseEntity<>(new BookGenreReadDTO(genre), HttpStatus.OK);
    }

    @GetMapping
    public List<BookGenreCollectionDTO> getAllGenres()
    {
        return bookGenreService.findAll().stream()
                .map(genre -> new BookGenreCollectionDTO(genre.getId(), genre.getName()))
                .collect(Collectors.toList());
    }

    @PutMapping("/{genreId}")
    public ResponseEntity<?> updateBook(@PathVariable UUID genreId, @RequestBody BookGenreCreateUpdateDTO bookDto)
    {
        Optional<BookGenre> existingGenreOptional = bookGenreService.findById(genreId);

        if (existingGenreOptional.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genre not found with ID: " + genreId);
        }

        // Update existing book details
        BookGenre existingGenre = existingGenreOptional.get();
        existingGenre.setName(bookDto.getName());
        existingGenre.setPopularity(bookDto.getPopularity());

        // Save the updated book
        bookGenreService.save(existingGenre);

        return ResponseEntity.ok(new BookGenreCreateUpdateDTO(existingGenre)); // Return updated book as DTO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable UUID id)
    {
        if (bookGenreService.existsById(id))
        {
            bookGenreService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found");
        }
    }
}
