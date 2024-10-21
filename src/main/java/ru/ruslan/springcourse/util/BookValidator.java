package ru.ruslan.springcourse.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ruslan.springcourse.dao.BookDAO;
import ru.ruslan.springcourse.models.Book;

@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;

    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if(bookDAO.show(book.getName()).isPresent())
            errors.rejectValue("name", "", "Книга с таким названием уже есть");
    }
}
