package com.library.controller;

import com.library.entity.Author;
import com.library.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // ─── Read: list all authors ───────────────────────────────────────────────
    @GetMapping
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authors/list";
    }

    // ─── Create: show add form ────────────────────────────────────────────────
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        model.addAttribute("pageTitle", "Add New Author");
        return "authors/add";
    }

    // ─── Create: handle form submission ──────────────────────────────────────
    @PostMapping("/new")
    public String addAuthor(@Valid @ModelAttribute("author") Author author,
                            BindingResult result,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Add New Author");
            return "authors/add";
        }
        try {
            authorService.saveAuthor(author);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Author '" + author.getName() + "' added successfully!");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage",
                    "An author with email '" + author.getEmail() + "' already exists.");
            model.addAttribute("pageTitle", "Add New Author");
            return "authors/add";
        }
    }

    // ─── Update: show edit form ───────────────────────────────────────────────
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        model.addAttribute("pageTitle", "Edit Author");
        return "authors/edit";
    }

    // ─── Update: handle update submission ────────────────────────────────────
    @PostMapping("/edit/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @Valid @ModelAttribute("author") Author authorDetails,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            authorDetails.setId(id);
            model.addAttribute("pageTitle", "Edit Author");
            return "authors/edit";
        }
        try {
            authorService.updateAuthor(id, authorDetails);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Author updated successfully!");
            return "redirect:/authors";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage",
                    "An author with email '" + authorDetails.getEmail() + "' already exists.");
            authorDetails.setId(id);
            model.addAttribute("pageTitle", "Edit Author");
            return "authors/edit";
        }
    }
}
