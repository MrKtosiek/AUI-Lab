package org.example;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BookGenre implements Comparable<BookGenre>, Serializable
{
    String name;
    double popularity;
    @Builder.Default
    List<Book> books = new ArrayList<>();

    public void AddBook(Book book)
    {
        books.add(book);
        book.setGenre(this);
    }

    @Override
    public int compareTo(BookGenre o)
    {
        return Double.compare(popularity, o.popularity);
    }
}
