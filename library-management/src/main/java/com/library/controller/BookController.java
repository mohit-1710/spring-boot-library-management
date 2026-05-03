package com.library.controller;

import com.library.dto.BookWithAuthorDTO;
import com.library.entity.Author;
import com.library.entity.Book;
import com.library.service.AuthorService;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    // ─── Read: list all books ─────────────────────────────────────────────────
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list";
    }

    // ─── Read: inner join report ──────────────────────────────────────────────
    @GetMapping("/report")
    public String booksReport(Model model) {
        List<BookWithAuthorDTO> report = bookService.getBooksWithAuthors();
        model.addAttribute("report", report);
        return "books/report";
    }

    // ─── Create: show add form ────────────────────────────────────────────────
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("pageTitle", "Add New Book");
        return "books/add";
    }

    // ─── Create: handle form submission ──────────────────────────────────────
    @PostMapping("/new")
    public String addBook(@Valid @ModelAttribute("book") Book book,
                          BindingResult result,
                          @RequestParam(value = "authorId", required = false) Long authorId,
                          Model model,
                          RedirectAttributes redirectAttributes) {
        if (authorId == null) {
            result.rejectValue("author", "required", "Author is required");
        } else {
            Author author = authorService.getAuthorById(authorId);
            book.setAuthor(author);
        }

        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("pageTitle", "Add New Book");
            return "books/add";
        }

        try {
            bookService.saveBook(book);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Book '" + book.getTitle() + "' added successfully!");
            return "redirect:/books";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage",
                    "A book with ISBN '" + book.getIsbn() + "' already exists.");
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("pageTitle", "Add New Book");
            return "books/add";
        }
    }

    // ─── Update: show edit form ───────────────────────────────────────────────
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("selectedAuthorId", book.getAuthor().getId());
        model.addAttribute("pageTitle", "Edit Book");
        return "books/edit";
    }

    // ─── Update: handle update submission ────────────────────────────────────
    @PostMapping("/edit/{id}")
    public String updateBook(@PathVariable Long id,
                             @Valid @ModelAttribute("book") Book bookDetails,
                             BindingResult result,
                             @RequestParam(value = "authorId", required = false) Long authorId,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (authorId == null) {
            result.rejectValue("author", "required", "Author is required");
        } else {
            Author author = authorService.getAuthorById(authorId);
            bookDetails.setAuthor(author);
        }

        if (result.hasErrors()) {
            bookDetails.setId(id);
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("selectedAuthorId", authorId);
            model.addAttribute("pageTitle", "Edit Book");
            return "books/edit";
        }

        try {
            bookService.updateBook(id, bookDetails);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Book updated successfully!");
            return "redirect:/books";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage",
                    "A book with ISBN '" + bookDetails.getIsbn() + "' already exists.");
            bookDetails.setId(id);
            model.addAttribute("authors", authorService.getAllAuthors());
            model.addAttribute("selectedAuthorId", authorId);
            model.addAttribute("pageTitle", "Edit Book");
            return "books/edit";
        }
    }
}
