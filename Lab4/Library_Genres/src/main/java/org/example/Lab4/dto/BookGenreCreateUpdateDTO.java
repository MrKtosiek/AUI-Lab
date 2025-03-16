package org.example.Lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookGenreCreateUpdateDTO
{
    private String name;
    private double popularity;
}
