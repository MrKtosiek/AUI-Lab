package org.example.Lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedBookGenreCreateUpdateDTO
{
    private String name;
    private UUID id;
}
