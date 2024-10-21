package ru.ruslan.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ruslan.springcourse.models.Book;
import ru.ruslan.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> show(String name) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE name=?",
                new BeanPropertyRowMapper<>(Book.class), name).stream().findAny();
    }

    public Book show(int book_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id=?",
                new BeanPropertyRowMapper<>(Book.class), book_id).stream().findAny().orElse(null);
    }

    public List<Book> get(int person_id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?",
                new BeanPropertyRowMapper<>(Book.class), person_id);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, yearOfPublication) VALUES(?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYearOfPublication());
    }

    public void update(int book_id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name=?, author=?, yearOfPublication=? WHERE book_id=?",
                book.getName(), book.getAuthor(), book.getYearOfPublication(), book_id);
    }

    public void delete(int book_id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", book_id);
    }

    public void release(int book_id) {
        jdbcTemplate.update("UPDATE Book SET person_id=null WHERE book_id=?", book_id);
    }

    public void assign(int book_id, Person person) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", person.getPerson_id(), book_id);

    }
}