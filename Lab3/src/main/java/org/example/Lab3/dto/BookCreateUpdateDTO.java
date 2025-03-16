package org.example.Lab3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Lab3.model.Book;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateUpdateDTO
{
    private String name;
    private String authorFirstName;
    private String authorLastName;
    private double popularity;
    private UUID genreId;

    public BookCreateUpdateDTO(Book book) {
        this.name = book.getName();
        this.authorFirstName = book.getAuthorFirstName();
        this.authorLastName = book.getAuthorLastName();
        this.popularity = book.getPopularity();
        this.genreId = book.getGenre().getId();
    }
}
