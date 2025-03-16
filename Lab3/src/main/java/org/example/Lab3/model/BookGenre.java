package org.example.Lab3.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "genres")
public class BookGenre implements Comparable<BookGenre>, Serializable
{
    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();
    @Column(name = "name")
    String name;
    @Column(name = "popularity")
    double popularity;
    @Builder.Default
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
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
