package org.example.Lab3.dto;

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
