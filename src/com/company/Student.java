package com.company;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private String surname;
    private String group;
    private int day;
    private int month;
    private int year;

    public Student() {

    }

    public Student(String name, String surname, String group, int day, int month, int year) {
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void readAll() {
        System.out.println(this.name + "\t" + this.surname + "\t" + this.group + "\t" + this.day + "\t" + this.month + "\t" + this.year);
    }

    @Override
    public String toString() {
        return this.name + "-----" + this.surname + "-----" + this.group + "-----" + this.day + "/" + this.month + "/" + this.year;
    }
}