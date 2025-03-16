package org.example.Lab5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Lab5.model.BookGenre;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookGenreCreateUpdateDTO
{
    private String name;
    private double popularity;

    public BookGenreCreateUpdateDTO(BookGenre genre)
    {
        this.name = genre.getName();
        this.popularity = genre.getPopularity();
    }
}
