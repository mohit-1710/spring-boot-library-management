package com.library.repository;

import com.library.dto.BookWithAuthorDTO;
import com.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByGenre(String genre);

    List<Book> findByAuthorId(Long authorId);

    boolean existsByIsbn(String isbn);

    /**
     * Custom JPQL query performing an INNER JOIN between Book and Author entities.
     * Returns a projection DTO combining fields from both tables.
     */
    @Query("SELECT new com.library.dto.BookWithAuthorDTO(" +
           "b.id, b.title, b.isbn, b.publicationYear, b.genre, a.name, a.nationality) " +
           "FROM Book b INNER JOIN b.author a ORDER BY a.name, b.title")
    List<BookWithAuthorDTO> findAllBooksWithAuthors();
}
