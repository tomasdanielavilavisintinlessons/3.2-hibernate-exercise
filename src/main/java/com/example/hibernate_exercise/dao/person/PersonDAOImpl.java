package com.example.hibernate_exercise.dao.person;

import com.example.hibernate_exercise.connection.ConnectionHandler;
import com.example.hibernate_exercise.connection.ConnectionHandlerImpl;
import com.example.hibernate_exercise.model.Person;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {
    private ConnectionHandler connectionHandler = ConnectionHandlerImpl.getInstance();

    private static PersonDAO INSTANCE = new PersonDAOImpl();

    private PersonDAOImpl() {
    }

    public static PersonDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public Person retrievePersonById(int id) {
        return connectionHandler.getConnection().find(Person.class, id);
    }

    @Override
    public Person insertPerson(String name, String surname, int age) {
        connectionHandler.getConnection().getTransaction().begin();

        Person person = new Person(name, surname, age);
        connectionHandler.getConnection().persist(person);

        connectionHandler.getConnection().getTransaction().commit();
        return person;
    }

    @Override
    public void deletePersonById(int id) {
        connectionHandler.getConnection().getTransaction().begin();

        Person personToDelete = connectionHandler.getConnection().find(Person.class, id);
        connectionHandler.getConnection().remove(personToDelete);

        connectionHandler.getConnection().getTransaction().commit();
    }

    @Override
    public void updatePersonName(int id, String name) {
        connectionHandler.getConnection().getTransaction().begin();

        Person personToUpdate = connectionHandler.getConnection().find(Person.class, id);
        personToUpdate.setName(name);

        connectionHandler.getConnection().getTransaction().commit();
    }

    @Override
    public List<Person> retrieveAll() {
        List<Person> result = new ArrayList<>();

        Query query = connectionHandler.getConnection().createQuery("SELECT p FROM Person p");
        result = query.getResultList();

        return result;
    }
}
