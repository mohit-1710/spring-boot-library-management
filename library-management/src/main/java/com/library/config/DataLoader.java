package com.library.config;

import com.library.entity.Author;
import com.library.entity.Book;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public DataLoader(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {
        if (authorRepository.count() > 0) return;

        Author a1  = authorRepository.save(new Author("J.K. Rowling",          "jk.rowling@example.com",      1965, "British"));
        Author a2  = authorRepository.save(new Author("George Orwell",          "george.orwell@example.com",   1903, "British"));
        Author a3  = authorRepository.save(new Author("Ernest Hemingway",       "e.hemingway@example.com",     1899, "American"));
        Author a4  = authorRepository.save(new Author("Gabriel García Márquez", "garcia.marquez@example.com",  1927, "Colombian"));
        Author a5  = authorRepository.save(new Author("Toni Morrison",          "toni.morrison@example.com",   1931, "American"));
        Author a6  = authorRepository.save(new Author("Haruki Murakami",        "h.murakami@example.com",      1949, "Japanese"));
        Author a7  = authorRepository.save(new Author("Leo Tolstoy",            "leo.tolstoy@example.com",     1828, "Russian"));
        Author a8  = authorRepository.save(new Author("Franz Kafka",            "franz.kafka@example.com",     1883, "Czech"));
        Author a9  = authorRepository.save(new Author("Virginia Woolf",         "v.woolf@example.com",         1882, "British"));
        Author a10 = authorRepository.save(new Author("Mark Twain",             "mark.twain@example.com",      1835, "American"));

        bookRepository.saveAll(List.of(
            new Book("Harry Potter and the Philosopher's Stone", "9780439708180", 1997, "Fantasy",            a1),
            new Book("Nineteen Eighty-Four",                     "9780451524935", 1949, "Dystopian Fiction",  a2),
            new Book("The Old Man and the Sea",                  "9780684801223", 1952, "Literary Fiction",   a3),
            new Book("One Hundred Years of Solitude",            "9780060883287", 1967, "Magical Realism",    a4),
            new Book("Beloved",                                  "9781400033416", 1987, "Historical Fiction", a5),
            new Book("Norwegian Wood",                           "9780375704024", 1987, "Literary Fiction",   a6),
            new Book("War and Peace",                            "9781400079988", 1869, "Historical Fiction", a7),
            new Book("The Metamorphosis",                        "9780553213690", 1915, "Absurdist Fiction",  a8),
            new Book("Mrs Dalloway",                             "9780156628709", 1925, "Literary Fiction",   a9),
            new Book("Adventures of Huckleberry Finn",           "9780486280615", 1884, "Adventure",          a10)
        ));
    }
}
