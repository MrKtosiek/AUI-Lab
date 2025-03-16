package org.example.Lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Lab4.model.Book;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookReadDTO
{
    private UUID id;
    private String name;
    private String authorFirstName;
    private String authorLastName;
    private double popularity;
    private SimplifiedBookGenreReadDTO genre;

    public BookReadDTO(Book book)
    {
        this.id = book.getId();
        this.name = book.getName();
        this.authorFirstName = book.getAuthorFirstName();
        this.authorLastName = book.getAuthorLastName();
        this.popularity = book.getPopularity();
        this.genre = new SimplifiedBookGenreReadDTO(book.getGenre());
    }
}
