package com.example.hibernate_exercise.connection;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;

public interface ConnectionHandler {
    Session getConnection();
}
