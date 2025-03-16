package org.example.Lab5.service;

import org.example.Lab5.dto.SimplifiedBookGenreCreateUpdateDTO;
import org.example.Lab5.model.BookGenre;
import org.example.Lab5.repository.BookGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookGenreService
{
    private final BookGenreRepository bookGenreRepository;
    private final RestTemplate restTemplate;

    @Value("${elements.service.url}")
    private String elementsServiceUrl;

    @Autowired
    public BookGenreService(BookGenreRepository bookGenreRepository, RestTemplate restTemplate)
    {
        this.bookGenreRepository = bookGenreRepository;
        this.restTemplate = restTemplate;
    }

    public BookGenre save(BookGenre bookGenre)
    {
        boolean isNew = !bookGenreRepository.existsById(bookGenre.getId());
        BookGenre savedGenre = bookGenreRepository.save(bookGenre);
        if (isNew)
        {
            notifyElementsService(savedGenre);
        }
        return savedGenre;
    }

    public Optional<BookGenre> findById(UUID id)
    {
        return bookGenreRepository.findById(id);
    }

    public List<BookGenre> findAll()
    {
        return bookGenreRepository.findAll();
    }

    public boolean existsById(UUID id)
    {
        return bookGenreRepository.existsById(id);
    }

    public void deleteById(UUID id)
    {
        if (bookGenreRepository.existsById(id))
        {
            bookGenreRepository.deleteById(id);
            notifyElementsServiceDelete(id);
        }
        else
        {
            throw new IllegalArgumentException("Genre with ID " + id + " does not exist");
        }
    }

    private void notifyElementsService(BookGenre genre)
    {
        SimplifiedBookGenreCreateUpdateDTO genreDto = new SimplifiedBookGenreCreateUpdateDTO(genre.getName(), genre.getId());
        String url = elementsServiceUrl + "/api/simplified-genres";
        restTemplate.postForEntity(url, genreDto, Void.class);
    }

    private void notifyElementsServiceDelete(UUID id)
    {
        String url = elementsServiceUrl + "/api/simplified-genres/" + id;
        restTemplate.delete(url);
    }
}
