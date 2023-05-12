package com.studentregistry.dto;

public class NewStudentInputData {
    public String name;
    public String surname;
    public Integer course;
    public String group;

    public NewStudentInputData(String name, String surname, Integer course, String group) {
        this.name = name;
        this.surname = surname;
        this.course = course;
        this.group = group;
    }
}
