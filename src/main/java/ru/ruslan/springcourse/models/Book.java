package ru.ruslan.springcourse.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int book_id;

    @NotEmpty(message = "У книги должно быть название")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до 100 символов")
    private String name;

    @NotEmpty(message = "У автора должно быть имя или псевдоним")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 знаков")
    private String author;

    @Min(value = 1901, message = "Год публикации должен быть не ранее 1901")
    @Max(value = 2024, message = "Год публикации должен быть не позднее 2024")
    private int yearOfPublication;
    private Integer person_id;

    public Book() {

    }

    public Book(int book_id, String name, String author, int yearOfPublication, Integer person_id) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.person_id = person_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public boolean personIsPresent() {
        return person_id != null;
    }
}