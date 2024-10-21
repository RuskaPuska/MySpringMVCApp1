package ru.ruslan.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ruslan.springcourse.dao.BookDAO;
import ru.ruslan.springcourse.dao.PersonDAO;
import ru.ruslan.springcourse.models.Book;
import ru.ruslan.springcourse.models.Person;
import ru.ruslan.springcourse.util.BookValidator;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{book_id}")
    public String show(@PathVariable("book_id") int book_id, Model model,
                       @ModelAttribute("formPerson") Person formPerson) {
        model.addAttribute("book", bookDAO.show(book_id));
        model.addAttribute("person", personDAO.get(book_id));
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if(bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{book_id}/edit")
    public String edit(@PathVariable("book_id") int book_id, Model model) {
        model.addAttribute("book", bookDAO.show(book_id));
        return "books/edit";
    }

    @PatchMapping("/{book_id}")
    public String update(@PathVariable("book_id") int book_id,
                         @ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);

        if(bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(book_id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{book_id}")
    public String delete(@PathVariable("book_id") int book_id) {
        bookDAO.delete(book_id);
        return "redirect:/books";
    }

    @PatchMapping("/{book_id}/release")
    public String release(@PathVariable("book_id") int book_id) {
        bookDAO.release(book_id);
        return "redirect:/books/{book_id}";
    }

    @PatchMapping("/{book_id}/assign")
    public String assign(@PathVariable("book_id") int book_id, @ModelAttribute Person person) {
        bookDAO.assign(book_id, person);
        return "redirect:/books/{book_id}";
    }
}