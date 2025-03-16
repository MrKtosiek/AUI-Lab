package org.example;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
public class Book implements Comparable<Book>, Serializable
{
    String name;
    String authorFirstName;
    String authorLastName;
    double popularity;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    BookGenre genre;

    public BookDTO CreateDTO()
    {
        return new BookDTO(name, authorFirstName, authorLastName, popularity, genre.name);
    }

    @Override
    public int compareTo(Book o)
    {
        return authorLastName.compareTo(o.authorLastName);
    }
}
