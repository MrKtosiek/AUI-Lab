package org.example.Lab4.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
public class Book implements Comparable<Book>, Serializable
{
    @Id
    @Builder.Default
    private UUID id = UUID.randomUUID();
    @Column(name = "name")
    String name;
    @Column(name = "author_first_name")
    String authorFirstName;
    @Column(name = "author_last_name")
    String authorLastName;
    @Column(name = "popularity")
    double popularity;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "genre_id")
    SimplifiedBookGenre genre;

    @Override
    public int compareTo(Book o)
    {
        return authorLastName.compareTo(o.authorLastName);
    }
}
