package ru.ruslan.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.ruslan.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fullName=?",
                new BeanPropertyRowMapper<>(Person.class), fullName).stream().findAny();
    }

    public Person show(int person_id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?",
                new BeanPropertyRowMapper<>(Person.class), person_id).stream().findAny().orElse(null);
    }

    public Person get(int book_id) {
        return jdbcTemplate.query("SELECT * FROM Person JOIN Book on Person.person_id=Book.person_id WHERE Book.book_id=?",
                new BeanPropertyRowMapper<>(Person.class), book_id).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fullName, yearOfBirth) VALUES(?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }

    public void update(int person_id, Person person) {
        jdbcTemplate.update("UPDATE Person SET fullName=?, yearOfBirth=? WHERE person_id=?",
                person.getFullName(), person.getYearOfBirth(), person_id);
    }

    public void delete(int person_id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", person_id);
    }
}