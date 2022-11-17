package com.example.hibernate_exercise.connection;

import com.example.hibernate_exercise.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class ConnectionHandlerImpl implements ConnectionHandler {
    private static SessionFactory sessionFactory;
    private Session session;

    private static ConnectionHandler INSTANCE = new ConnectionHandlerImpl();

    private ConnectionHandlerImpl() {
        Configuration config = new Configuration()
                .configure()
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(CourseEnrollment.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Professor.class)
                .addAnnotatedClass(Person.class);

        StandardServiceRegistry reg = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .build();

        sessionFactory = config.buildSessionFactory(reg);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        session = sessionFactory.openSession();
    }

    public static ConnectionHandler getInstance() {
        return INSTANCE;
    }

    @Override
    public Session getConnection() {
        return session;
    }
}
