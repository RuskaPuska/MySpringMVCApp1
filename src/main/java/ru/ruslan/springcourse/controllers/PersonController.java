package ru.ruslan.springcourse.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.ruslan.springcourse.dao.BookDAO;
import ru.ruslan.springcourse.dao.PersonDAO;
import ru.ruslan.springcourse.models.Person;
import ru.ruslan.springcourse.util.PersonValidator;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PersonController(PersonDAO personDAO, BookDAO bookDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
        this.personValidator = personValidator;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{person_id}")
    public String show(@PathVariable("person_id") int person_id, Model model) {
        model.addAttribute("person", personDAO.show(person_id));
        model.addAttribute("books", bookDAO.get(person_id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{person_id}/edit")
    public String edit(@PathVariable("person_id") int person_id, Model model) {
        model.addAttribute("person", personDAO.show(person_id));
        return "people/edit";
    }

    @PatchMapping("/{person_id}")
    public String update(@PathVariable("person_id") int person_id, @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(person_id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{person_id}")
    public String delete(@PathVariable("person_id") int person_id) {
        personDAO.delete(person_id);
        return "redirect:/people";
    }
}