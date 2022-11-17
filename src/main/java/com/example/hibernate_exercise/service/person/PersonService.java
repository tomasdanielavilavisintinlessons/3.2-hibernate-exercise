package com.example.hibernate_exercise.service.person;

import com.example.hibernate_exercise.model.Person;

import java.util.List;

public interface PersonService {
    Person retrievePersonById(int id);
    Person insertPerson(String name, String surname, int age);
    void deletePersonById(int id);
    void updatePersonName(int id, String name);
    List<Person> retrieveAll();
}
