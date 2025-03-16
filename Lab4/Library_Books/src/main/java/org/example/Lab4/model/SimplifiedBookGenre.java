package org.example.Lab4.model;

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
@Table(name = "simplified_genres")
public class SimplifiedBookGenre implements Serializable
{
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Book> books = new ArrayList<>();
}
