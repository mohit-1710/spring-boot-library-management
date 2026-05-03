package com.library.service;

import com.library.entity.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();

    Author getAuthorById(Long id);

    Author saveAuthor(Author author);

    Author updateAuthor(Long id, Author authorDetails);

    boolean existsByEmail(String email);
}
