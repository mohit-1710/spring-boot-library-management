package com.library.repository;

import com.library.dto.BookWithAuthorDTO;
import com.library.entity.Author;
import com.library.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;
    private Book savedBook;

    @BeforeEach
    void setUp() {
        author = authorRepository.save(
                new Author("Test Author", "test.author@test.com", 1970, "American"));
        savedBook = bookRepository.save(
                new Book("Test Book", "9781234567890", 2000, "Fiction", author));
    }

    @Test
    void testSaveBook_persistsSuccessfully() {
        assertThat(savedBook.getId()).isNotNull();
        assertThat(savedBook.getTitle()).isEqualTo("Test Book");
        assertThat(savedBook.getAuthor().getName()).isEqualTo("Test Author");
    }

    @Test
    void testFindById_returnsBook() {
        Optional<Book> found = bookRepository.findById(savedBook.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getIsbn()).isEqualTo("9781234567890");
    }

    @Test
    void testFindByIsbn_returnsMatchingBook() {
        Optional<Book> found = bookRepository.findByIsbn("9781234567890");
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Test Book");
    }

    @Test
    void testFindByGenre_returnsMatchingBooks() {
        bookRepository.save(new Book("Another Book", "9780000000001", 2001, "Fiction", author));
        List<Book> fictionBooks = bookRepository.findByGenre("Fiction");
        assertThat(fictionBooks).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void testFindByAuthorId_returnsAuthorBooks() {
        List<Book> books = bookRepository.findByAuthorId(author.getId());
        assertThat(books).isNotEmpty();
        assertThat(books.get(0).getAuthor().getId()).isEqualTo(author.getId());
    }

    @Test
    void testFindAllBooksWithAuthors_innerJoinQuery() {
        List<BookWithAuthorDTO> result = bookRepository.findAllBooksWithAuthors();
        assertThat(result).isNotEmpty();
        BookWithAuthorDTO dto = result.get(0);
        assertThat(dto.getBookTitle()).isNotBlank();
        assertThat(dto.getAuthorName()).isNotBlank();
        assertThat(dto.getIsbn()).isNotBlank();
    }

    @Test
    void testFindAllBooksWithAuthors_dtoContainsCorrectData() {
        List<BookWithAuthorDTO> result = bookRepository.findAllBooksWithAuthors();
        assertThat(result).anyMatch(dto ->
                dto.getBookTitle().equals("Test Book") &&
                dto.getAuthorName().equals("Test Author"));
    }

    @Test
    void testDuplicateIsbn_throwsDataIntegrityViolationException() {
        Book duplicate = new Book("Duplicate ISBN Book", "9781234567890", 2005, "Mystery", author);
        assertThatThrownBy(() -> {
            bookRepository.save(duplicate);
            bookRepository.flush();
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void testExistsByIsbn_returnsTrueForExistingIsbn() {
        assertThat(bookRepository.existsByIsbn("9781234567890")).isTrue();
    }

    @Test
    void testUpdateBook_changesFieldsCorrectly() {
        savedBook.setTitle("Updated Title");
        savedBook.setPublicationYear(2010);
        Book updated = bookRepository.save(savedBook);
        assertThat(updated.getTitle()).isEqualTo("Updated Title");
        assertThat(updated.getPublicationYear()).isEqualTo(2010);
    }
}
