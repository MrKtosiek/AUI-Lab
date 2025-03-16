package org.example.Lab5.model;

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
    @Override
    public int compareTo(BookGenre o)
    {
        return Double.compare(popularity, o.popularity);
    }
}
