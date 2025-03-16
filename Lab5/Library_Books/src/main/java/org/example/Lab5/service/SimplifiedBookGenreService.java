package org.example.Lab5.service;

import org.example.Lab5.model.SimplifiedBookGenre;
import org.example.Lab5.repository.SimplifiedBookGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SimplifiedBookGenreService
{
    private final SimplifiedBookGenreRepository genreRepository;

    @Autowired
    public SimplifiedBookGenreService(SimplifiedBookGenreRepository genreRepository)
    {
        this.genreRepository = genreRepository;
    }

    public List<SimplifiedBookGenre> findAll()
    {
        return genreRepository.findAll();
    }

    public SimplifiedBookGenre save(SimplifiedBookGenre genre)
    {
        return genreRepository.save(genre);
    }

    public void deleteById(UUID id)
    {
        genreRepository.deleteById(id);
    }

    public Optional<SimplifiedBookGenre> findById(UUID id)
    {
        return genreRepository.findById(id);
    }
}
