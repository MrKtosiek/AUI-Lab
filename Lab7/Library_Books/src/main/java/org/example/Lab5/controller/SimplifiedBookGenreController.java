package org.example.Lab5.controller;

import org.example.Lab5.dto.SimplifiedBookGenreCreateUpdateDTO;
import org.example.Lab5.dto.SimplifiedBookGenreReadDTO;
import org.example.Lab5.model.SimplifiedBookGenre;
import org.example.Lab5.service.SimplifiedBookGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/simplified-genres")
public class SimplifiedBookGenreController
{
    private final SimplifiedBookGenreService genreService;

    @Autowired
    public SimplifiedBookGenreController(SimplifiedBookGenreService genreService)
    {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<SimplifiedBookGenreReadDTO> addSimplifiedGenre(
            @RequestBody SimplifiedBookGenreCreateUpdateDTO genreDto)
    {
        SimplifiedBookGenre genre = SimplifiedBookGenre.builder()
                .name(genreDto.getName())
                .id(genreDto.getId())
                .build();
        SimplifiedBookGenre savedGenre = genreService.save(genre);
        return new ResponseEntity<>(new SimplifiedBookGenreReadDTO(savedGenre), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSimplifiedGenre(@PathVariable UUID id)
    {
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SimplifiedBookGenreReadDTO> getSimplifiedGenre(@PathVariable UUID id)
    {
        SimplifiedBookGenre genre = genreService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre not found"));
        return ResponseEntity.ok(new SimplifiedBookGenreReadDTO(genre));
    }

    @GetMapping
    public List<SimplifiedBookGenreReadDTO> getAllSimplifiedGenres()
    {
        return genreService.findAll().stream()
                .map(genre -> new SimplifiedBookGenreReadDTO(genre.getId(), genre.getName()))
                .collect(Collectors.toList());
    }

    @PostMapping("/sync")
    public ResponseEntity<Void> syncGenre(@RequestBody SimplifiedBookGenreReadDTO genreDto)
    {
        SimplifiedBookGenre genre = SimplifiedBookGenre.builder()
                .id(genreDto.getId())
                .name(genreDto.getName())
                .build();
        genreService.save(genre);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/sync/{id}")
    public ResponseEntity<Void> removeGenre(@PathVariable UUID id)
    {
        genreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
