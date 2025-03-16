package org.example.Lab4;

import org.example.Lab4.model.Book;
import org.example.Lab4.model.BookGenre;
import org.example.Lab4.service.BookGenreService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Component
@Order(2)
public class AppCommandLineRunner implements CommandLineRunner
{

    private final BookGenreService bookGenreService;

    @Autowired
    public AppCommandLineRunner(BookGenreService bookGenreService)
    {
        this.bookGenreService = bookGenreService;
    }

    private static final String listCategoriesCommand = "LISTC";
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
            System.out.println(exitCommand + " - Exit the application");
            System.out.print("Enter command: ");
            String command = scanner.nextLine().trim().toUpperCase();

            switch (command)
            {
                case listCategoriesCommand:
                    listCategories();
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
        List<BookGenre> categories = bookGenreService.findAll();
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
}
