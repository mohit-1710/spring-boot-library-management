package com.library.service;

import com.library.dto.BookWithAuthorDTO;
import com.library.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book saveBook(Book book);

    Book updateBook(Long id, Book bookDetails);

    List<BookWithAuthorDTO> getBooksWithAuthors();

    boolean existsByIsbn(String isbn);
}
