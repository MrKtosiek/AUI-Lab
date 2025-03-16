package org.example;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class BookDTO implements Comparable<BookDTO>, Serializable
{
    String name;
    String authorFirstName;
    String authorLastName;
    double popularity;
    String genre;

    @Override
    public int compareTo(BookDTO o)
    {
        return authorLastName.compareTo(o.authorLastName);
    }
}
