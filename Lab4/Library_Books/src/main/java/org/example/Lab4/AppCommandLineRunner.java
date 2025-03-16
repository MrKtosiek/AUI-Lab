package org.example.Lab4;

import org.example.Lab4.model.Book;
import org.example.Lab4.model.SimplifiedBookGenre;
import org.example.Lab4.service.SimplifiedBookGenreService;
import org.example.Lab4.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Component
@Order(2)
public class AppCommandLineRunner implements CommandLineRunner
{
    private final SimplifiedBookGenreService bookGenreService;
    private final BookService bookService;

    @Autowired
    public AppCommandLineRunner(SimplifiedBookGenreService bookGenreService, BookService bookService)
    {
        this.bookGenreService = bookGenreService;
        this.bookService = bookService;
    }

    private static final String listCategoriesCommand = "LISTC";
    private static final String listBooksCommand = "LISTB";
    private static final String addBookCommand = "ADD";
    private static final String deleteBookCommand = "DEL";
    private static final String exitCommand = "EXIT";

    @Override
    public void run(String... args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running)
        {
            System.out.println("\nAvailable commands:");
            System.out.println(listCategoriesCommand + " - List all categories");
            System.out.println(listBooksCommand + " - List all books");
            System.out.println(addBookCommand + " - Add a new book");
            System.out.println(deleteBookCommand + " - Delete an book by ID");
            System.out.println(exitCommand + " - Exit the application");
            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim().toUpperCase();

            switch (command)
            {
                case listCategoriesCommand:
                    listCategories();
                    break;
                case listBooksCommand:
                    listBooks();
                    break;
                case addBookCommand:
                    addBook(scanner);
                    break;
                case deleteBookCommand:
                    deleteBook(scanner);
                    break;
                case exitCommand:
                    running = false;
                    System.out.println("Exiting application.");
                    System.exit(0);
                default:
                    System.out.println("Unknown command. Please try again.");
            }
        }
        scanner.close();
    }

    private void listCategories()
    {
        List<SimplifiedBookGenre> categories = bookGenreService.findAll();
        if (categories.isEmpty())
        {
            System.out.println("No categories found.");
        }
        else
        {
            System.out.println("Categories:");
            categories.forEach(bookGenre -> System.out.println(" - " + bookGenre.getName() + " (ID: " + bookGenre.getId() + ")"));
        }
    }

    private void listBooks()
    {
        List<Book> books = bookService.findAll();
        if (books.isEmpty())
        {
            System.out.println("No books found.");
        }
        else
        {
            System.out.println("Books:");
            books.forEach(book -> System.out.println(" - " + book.getName() + " (ID: " + book.getId() + ", BookGenre: " + book.getGenre().getName() + ")"));
        }
    }

    private void addBook(Scanner scanner)
    {
        System.out.print("Enter book name: ");
        String bookName = scanner.nextLine().trim();
        System.out.print("Enter author's first name: ");
        String authorFirstName = scanner.nextLine().trim();
        System.out.print("Enter author's last name: ");
        String authorLastName = scanner.nextLine().trim();
        System.out.print("Enter book popularity: ");
        double bookPopularity;
        try
        {
            bookPopularity = scanner.nextDouble();
            scanner.nextLine();
        }
        catch (InputMismatchException e)
        {
            System.out.println("Invalid number format.");
            return;
        }

        System.out.println("Select bookGenre ID for the new book:");
        listCategories();
        System.out.print("Enter bookGenre ID: ");
        String bookGenreIdInput = scanner.nextLine().trim();

        try
        {
            UUID bookGenreId = UUID.fromString(bookGenreIdInput);
            Optional<SimplifiedBookGenre> bookGenreOptional = bookGenreService.findById(bookGenreId);

            if (bookGenreOptional.isPresent())
            {
                SimplifiedBookGenre bookGenre = bookGenreOptional.get();
                Book book = Book.builder()
                        .name(bookName)
                        .authorFirstName(authorFirstName)
                        .authorLastName(authorLastName)
                        .genre(bookGenre)
                        .popularity(bookPopularity)
                        .build();
                bookService.save(book);
                System.out.println("Book added successfully.");
            }
            else
            {
                System.out.println("BookGenre not found with ID: " + bookGenreId);
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Invalid UUID format.");
        }
    }

    private void deleteBook(Scanner scanner)
    {
        System.out.print("Enter the ID of the book to delete: ");
        String bookIdInput = scanner.nextLine().trim();

        try
        {
            UUID bookId = UUID.fromString(bookIdInput);
            if (bookService.existsById(bookId))
            {
                bookService.deleteById(bookId);
                System.out.println("Book deleted successfully.");
            }
            else
            {
                System.out.println("Book not found with ID: " + bookId);
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Invalid UUID format.");
        }
    }
}
