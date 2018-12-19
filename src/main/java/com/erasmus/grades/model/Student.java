package com.erasmus.grades.model;

public class Student extends User {

    private int year;

    public Student(int id, String name, String surname, String email) {
        this.iduser = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

}
