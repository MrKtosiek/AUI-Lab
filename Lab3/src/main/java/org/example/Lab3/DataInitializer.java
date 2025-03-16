package org.example.Lab3;

import org.example.Lab3.model.Book;
import org.example.Lab3.model.BookGenre;
import org.example.Lab3.service.BookGenreService;
import org.example.Lab3.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner
{
    private final BookGenreService bookGenreService;
    private final BookService bookService;

    @Autowired
    public DataInitializer(BookGenreService bookGenreService, BookService bookService)
    {
        this.bookGenreService = bookGenreService;
        this.bookService = bookService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception
    {
        BookGenre fantasy = BookGenre.builder().name("Fantasy").popularity(3.5).build();
        bookGenreService.save(fantasy);
        BookGenre scienceFiction = BookGenre.builder().name("Science Fiction").popularity(3.2).build();
        bookGenreService.save(scienceFiction);

        Book book1 = Book.builder().name("The Hobbit").authorFirstName("J.R.R.").authorLastName("Tolkien").popularity(4.1).build();
        fantasy.AddBook(book1);
        bookService.save(book1);
        Book book2 = Book.builder().name("Dune").authorFirstName("Frank").authorLastName("Herbert").popularity(4.9).build();
        scienceFiction.AddBook(book2);
        bookService.save(book2);
        Book book3 = Book.builder().name("1984").authorFirstName("George").authorLastName("Orwell").popularity(3.7).build();
        scienceFiction.AddBook(book3);
        bookService.save(book3);
        Book book4 = Book.builder().name("Harry Potter").authorFirstName("JK").authorLastName("Rowling").popularity(2.4).build();
        fantasy.AddBook(book4);
        bookService.save(book4);
        Book book5 = Book.builder().name("Lord of the Rings").authorFirstName("J.R.R.").authorLastName("Tolkien").popularity(3.9).build();
        fantasy.AddBook(book5);
        bookService.save(book5);
    }
}
