package com.library.service;

import com.library.entity.Author;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.AuthorRepository;
import com.library.service.impl.AuthorServiceImpl;
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
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    private Author sampleAuthor;

    @BeforeEach
    void setUp() {
        sampleAuthor = new Author("Leo Tolstoy", "leo@test.com", 1828, "Russian");
        sampleAuthor.setId(1L);
    }

    @Test
    void testGetAllAuthors_returnsAllAuthors() {
        Author author2 = new Author("Kafka", "kafka@test.com", 1883, "Czech");
        author2.setId(2L);
        when(authorRepository.findAll()).thenReturn(Arrays.asList(sampleAuthor, author2));

        List<Author> result = authorService.getAllAuthors();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Leo Tolstoy");
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testGetAuthorById_returnsCorrectAuthor() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(sampleAuthor));

        Author result = authorService.getAuthorById(1L);

        assertThat(result.getName()).isEqualTo("Leo Tolstoy");
        assertThat(result.getNationality()).isEqualTo("Russian");
        verify(authorRepository).findById(1L);
    }

    @Test
    void testGetAuthorById_throwsResourceNotFoundException_whenNotFound() {
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.getAuthorById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void testSaveAuthor_savesAndReturnsAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(sampleAuthor);

        Author result = authorService.saveAuthor(sampleAuthor);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getEmail()).isEqualTo("leo@test.com");
        verify(authorRepository, times(1)).save(sampleAuthor);
    }

    @Test
    void testUpdateAuthor_updatesAndReturnsModifiedAuthor() {
        Author updatedDetails = new Author("Lev Tolstoy", "lev@test.com", 1828, "Russian");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(sampleAuthor));
        when(authorRepository.save(any(Author.class))).thenAnswer(inv -> inv.getArgument(0));

        Author result = authorService.updateAuthor(1L, updatedDetails);

        assertThat(result.getName()).isEqualTo("Lev Tolstoy");
        assertThat(result.getEmail()).isEqualTo("lev@test.com");
        verify(authorRepository).save(any(Author.class));
    }

    @Test
    void testUpdateAuthor_throwsException_whenAuthorNotFound() {
        when(authorRepository.findById(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> authorService.updateAuthor(999L, sampleAuthor))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void testExistsByEmail_returnsTrue_forExistingEmail() {
        when(authorRepository.existsByEmail("leo@test.com")).thenReturn(true);

        boolean result = authorService.existsByEmail("leo@test.com");

        assertThat(result).isTrue();
        verify(authorRepository).existsByEmail("leo@test.com");
    }

    @Test
    void testExistsByEmail_returnsFalse_forNewEmail() {
        when(authorRepository.existsByEmail("new@test.com")).thenReturn(false);

        boolean result = authorService.existsByEmail("new@test.com");

        assertThat(result).isFalse();
    }
}
