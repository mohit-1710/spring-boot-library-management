package com.library.repository;

import com.library.entity.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    private Author savedAuthor;

    @BeforeEach
    void setUp() {
        savedAuthor = authorRepository.save(
                new Author("Jane Austen", "jane.austen@test.com", 1775, "British"));
    }

    @Test
    void testSaveAuthor_persistsSuccessfully() {
        assertThat(savedAuthor.getId()).isNotNull();
        assertThat(savedAuthor.getName()).isEqualTo("Jane Austen");
    }

    @Test
    void testFindById_returnsAuthor() {
        Optional<Author> found = authorRepository.findById(savedAuthor.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("jane.austen@test.com");
    }

    @Test
    void testFindByEmail_returnsMatchingAuthor() {
        Optional<Author> found = authorRepository.findByEmail("jane.austen@test.com");
        assertThat(found).isPresent();
        assertThat(found.get().getNationality()).isEqualTo("British");
    }

    @Test
    void testFindByEmail_returnsEmptyForUnknownEmail() {
        Optional<Author> found = authorRepository.findByEmail("nobody@example.com");
        assertThat(found).isNotPresent();
    }

    @Test
    void testFindAll_returnsAllPersistedAuthors() {
        authorRepository.save(new Author("Charles Dickens", "c.dickens@test.com", 1812, "British"));
        List<Author> authors = authorRepository.findAll();
        assertThat(authors).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void testUpdateAuthor_changesFieldsCorrectly() {
        savedAuthor.setNationality("English");
        savedAuthor.setBirthYear(1776);
        Author updated = authorRepository.save(savedAuthor);
        assertThat(updated.getNationality()).isEqualTo("English");
        assertThat(updated.getBirthYear()).isEqualTo(1776);
    }

    @Test
    void testExistsByEmail_returnsTrueForExistingEmail() {
        assertThat(authorRepository.existsByEmail("jane.austen@test.com")).isTrue();
    }

    @Test
    void testExistsByEmail_returnsFalseForNewEmail() {
        assertThat(authorRepository.existsByEmail("new@example.com")).isFalse();
    }

    @Test
    void testDuplicateEmail_throwsDataIntegrityViolationException() {
        Author duplicate = new Author("J. Austen", "jane.austen@test.com", 1775, "British");
        assertThatThrownBy(() -> {
            authorRepository.save(duplicate);
            authorRepository.flush();
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void testFindByNameContainingIgnoreCase_returnsCaseInsensitiveResults() {
        List<Author> results = authorRepository.findByNameContainingIgnoreCase("jane");
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).getName()).containsIgnoringCase("jane");
    }
}
