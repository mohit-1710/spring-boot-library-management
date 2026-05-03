package com.library.service;

import com.library.dto.BookWithAuthorDTO;
import com.library.entity.Author;
import com.library.entity.Book;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import com.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Author sampleAuthor;
    private Book sampleBook;

    @BeforeEach
    void setUp() {
        sampleAuthor = new Author("Virginia Woolf", "v.woolf@test.com", 1882, "British");
        sampleAuthor.setId(1L);

        sampleBook = new Book("Mrs Dalloway", "9780156628709", 1925, "Literary Fiction", sampleAuthor);
        sampleBook.setId(1L);
    }

    @Test
    void testGetAllBooks_returnsAllBooks() {
        Book book2 = new Book("Orlando", "9780156701600", 1928, "Literary Fiction", sampleAuthor);
        book2.setId(2L);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(sampleBook, book2));

        List<Book> result = bookService.getAllBooks();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("Mrs Dalloway");
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetBookById_returnsCorrectBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        Book result = bookService.getBookById(1L);

        assertThat(result.getTitle()).isEqualTo("Mrs Dalloway");
        assertThat(result.getIsbn()).isEqualTo("9780156628709");
        verify(bookRepository).findById(1L);
    }

    @Test
    void testGetBookById_throwsResourceNotFoundException_whenNotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.getBookById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void testSaveBook_savesAndReturnsBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(sampleBook);

        Book result = bookService.saveBook(sampleBook);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getAuthor().getName()).isEqualTo("Virginia Woolf");
        verify(bookRepository, times(1)).save(sampleBook);
    }

    @Test
    void testUpdateBook_updatesAndReturnsModifiedBook() {
        Book updatedDetails = new Book("Mrs Dalloway (Revised)", "9780156628709", 1926, "Classic", sampleAuthor);
        updatedDetails.setAuthor(sampleAuthor);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(sampleAuthor));
        when(bookRepository.save(any(Book.class))).thenAnswer(inv -> inv.getArgument(0));

        Book result = bookService.updateBook(1L, updatedDetails);

        assertThat(result.getTitle()).isEqualTo("Mrs Dalloway (Revised)");
        assertThat(result.getGenre()).isEqualTo("Classic");
        verify(bookRepository).save(any(Book.class));
    }

    @Test
    void testUpdateBook_throwsException_whenBookNotFound() {
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.updateBook(999L, sampleBook))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void testGetBooksWithAuthors_returnsInnerJoinResult() {
        BookWithAuthorDTO dto = new BookWithAuthorDTO(
                1L, "Mrs Dalloway", "9780156628709", 1925, "Literary Fiction", "Virginia Woolf", "British");
        when(bookRepository.findAllBooksWithAuthors()).thenReturn(List.of(dto));

        List<BookWithAuthorDTO> result = bookService.getBooksWithAuthors();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getBookTitle()).isEqualTo("Mrs Dalloway");
        assertThat(result.get(0).getAuthorName()).isEqualTo("Virginia Woolf");
        assertThat(result.get(0).getAuthorNationality()).isEqualTo("British");
        verify(bookRepository).findAllBooksWithAuthors();
    }

    @Test
    void testExistsByIsbn_returnsTrue_forExistingIsbn() {
        when(bookRepository.existsByIsbn("9780156628709")).thenReturn(true);

        boolean result = bookService.existsByIsbn("9780156628709");

        assertThat(result).isTrue();
    }

    @Test
    void testExistsByIsbn_returnsFalse_forNewIsbn() {
        when(bookRepository.existsByIsbn("9999999999")).thenReturn(false);

        boolean result = bookService.existsByIsbn("9999999999");

        assertThat(result).isFalse();
    }
}
