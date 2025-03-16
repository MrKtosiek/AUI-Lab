package org.example.Lab5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Lab5.model.SimplifiedBookGenre;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedBookGenreReadDTO
{
    private UUID id;
    private String name;

    public SimplifiedBookGenreReadDTO(SimplifiedBookGenre genre)
    {
        this.id = genre.getId();
        this.name = genre.getName();
    }
}
