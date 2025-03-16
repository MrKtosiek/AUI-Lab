package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main
{
    public static void main(String[] args)
    {
        // Task 2
        System.out.println("Task 2");

        List<BookGenre> genres = new ArrayList<>();

        BookGenre fantasy = BookGenre.builder().name("Fantasy").popularity(3.5).build();
        genres.add(fantasy);
        BookGenre scienceFiction = BookGenre.builder().name("Science Fiction").popularity(3.2).build();
        genres.add(scienceFiction);

        Book book1 = Book.builder().name("The Hobbit").authorFirstName("J.R.R.").authorLastName("Tolkien").popularity(4.1).build();
        fantasy.AddBook(book1);
        Book book2 = Book.builder().name("Dune").authorFirstName("Frank").authorLastName("Herbert").popularity(4.9).build();
        scienceFiction.AddBook(book2);
        Book book3 = Book.builder().name("1984").authorFirstName("George").authorLastName("Orwell").popularity(3.7).build();
        scienceFiction.AddBook(book3);
        Book book4 = Book.builder().name("Harry Potter").authorFirstName("JK").authorLastName("Rowling").popularity(2.4).build();
        fantasy.AddBook(book4);
        Book book5 = Book.builder().name("Lord of the Rings").authorFirstName("J.R.R.").authorLastName("Tolkien").popularity(3.9).build();
        fantasy.AddBook(book5);

        genres.forEach(genre -> {
            System.out.println(genre);
            genre.getBooks().forEach(book -> System.out.println("  " + book));
        });


        // Task 3
        System.out.println("Task 3");

        Set<Book> books = genres.stream()
                .flatMap(genre -> genre.getBooks().stream())
                .collect(Collectors.toSet());
        books.forEach(System.out::println);


        // Task 4
        System.out.println("Task 4");

        books.stream()
                .filter(book -> book.popularity > 4.0)
                .sorted(Comparator.comparing(book -> book.authorLastName))
                .forEach(System.out::println);


        // Task 5
        System.out.println("Task 5");

        List<BookDTO> bookDTOs = books.stream()
                .map(Book::CreateDTO)
                .sorted()
                .toList();
        bookDTOs.forEach(System.out::println);


        // Task 6
        System.out.println("Task 6");

        String filename = "bookGenres.bin";
        serializeGenres(genres, filename);
        List<BookGenre> deserializedGenres = deserializeGenres(filename);
        if (deserializedGenres != null)
        {
            deserializedGenres.forEach(genre -> {
                System.out.println(genre);
                genre.getBooks().forEach(book -> System.out.println("  " + book));
            });
        }


        // Task 7
        System.out.println("Task 7");

        executeParallelTask(genres, 2);
    }

    public static void serializeGenres(List<BookGenre> genres, String filePath)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath)))
        {
            oos.writeObject(genres);
            System.out.println("Genres have been serialized.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static List<BookGenre> deserializeGenres(String filePath)
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath)))
        {
            return (List<BookGenre>) ois.readObject();
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void executeParallelTask(List<BookGenre> genres, int poolSize)
    {
        ForkJoinPool customThreadPool = new ForkJoinPool(poolSize);

        try
        {
            customThreadPool.submit(() ->
                    genres.parallelStream().forEach(genre -> {
                        genre.getBooks().forEach(book -> {
                            try
                            {
                                Thread.sleep(500);
                                System.out.println(Thread.currentThread().getName() + " processing: " + book);
                            }
                            catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        });
                    })
            ).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            customThreadPool.shutdown();
        }
    }
}
