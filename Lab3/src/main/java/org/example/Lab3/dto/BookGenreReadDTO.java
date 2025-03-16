package org.example.Lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Lab3.model.BookGenre;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookGenreReadDTO
{
    private UUID id;
    private String name;
    private double popularity;

    public BookGenreReadDTO(BookGenre genre)
    {
        this.id = genre.getId();
        this.name = genre.getName();
        this.popularity = genre.getPopularity();
    }
}
