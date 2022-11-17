package com.example.hibernate_exercise.dao.person;

import com.example.hibernate_exercise.model.Person;

import java.util.List;

public interface PersonDAO {
    Person retrievePersonById(int id);
    Person insertPerson(String name, String surname, int age);
    void deletePersonById(int id);
    void updatePersonName(int id, String name);
    List<Person> retrieveAll();
}
