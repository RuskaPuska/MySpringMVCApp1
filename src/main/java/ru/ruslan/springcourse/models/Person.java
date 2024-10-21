package ru.ruslan.springcourse.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int person_id;

    @NotEmpty(message = "У человека должно быть ФИО (или хотя бы имя)")
    @Size(min = 2, max = 100, message = "ФИО должно быть от 2 до 100 знаков")
    private String fullName;

    @Min(value = 1901, message = "Год рождения должен быть не ранее 1901")
    @Max(value = 2020, message = "Год рождения должен быть не позднее 2020")
    private int yearOfBirth;

    public Person() {

    }

    public Person(int person_id, String fullName, int yearOfBirth) {
        this.person_id = person_id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }
}