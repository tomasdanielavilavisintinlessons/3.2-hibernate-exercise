package com.example.hibernate_exercise.dao.student;

import com.example.hibernate_exercise.connection.ConnectionHandler;
import com.example.hibernate_exercise.connection.ConnectionHandlerImpl;
import com.example.hibernate_exercise.model.Person;
import com.example.hibernate_exercise.model.Student;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private ConnectionHandler connectionHandler;

    private static StudentDAO INSTANCE = new StudentDAOImpl();

    private StudentDAOImpl() {
        this.connectionHandler = ConnectionHandlerImpl.getInstance();
    }

    public static StudentDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertStudent(Person person, int yearOfStudy, int enrollmentYear) {
        connectionHandler.getConnection().getTransaction().begin();

        Student student = new Student(person, yearOfStudy, enrollmentYear);
        connectionHandler.getConnection().persist(student);

        connectionHandler.getConnection().getTransaction().commit();
    }

    @Override
    public void deleteStudentBy(int id) {
        connectionHandler.getConnection().getTransaction().begin();

        Student studentToDelete = connectionHandler.getConnection()
                .find(Student.class, id);
        connectionHandler.getConnection().remove(studentToDelete);

        connectionHandler.getConnection().getTransaction().commit();
    }

    @Override
    public List<Student> retrieveFreeStudents() {
        List<Student> result = new ArrayList<>();

        Query query = connectionHandler.getConnection().createQuery(
                """
                    SELECT s
                    FROM Student s
                    WHERE s NOT IN(
                        SELECT ce.id.student
                        FROM CourseEnrollment ce
                        WHERE s = ce.id.student
                    )
                  """);
        result = query.getResultList();

        return result;
    }

    @Override
    public List<Student> retrieveAll() {
        List<Student> result = new ArrayList<>();

        Query query = connectionHandler.getConnection().createQuery("SELECT s FROM Student s");
        result = query.getResultList();

        return result;
    }
}
