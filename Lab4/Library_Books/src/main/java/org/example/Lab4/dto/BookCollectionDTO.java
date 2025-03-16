package org.example.Lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Lab4.model.Book;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCollectionDTO
{
    private UUID id;
    private String name;
    private String authorLastName;

    public BookCollectionDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.authorLastName = book.getAuthorLastName();
    }
}
