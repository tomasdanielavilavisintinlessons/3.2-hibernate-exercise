package com.example.hibernate_exercise.dao.course_enrollment;

import com.example.hibernate_exercise.connection.ConnectionHandler;
import com.example.hibernate_exercise.connection.ConnectionHandlerImpl;
import com.example.hibernate_exercise.model.*;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CourseEnrollmentDAOImpl implements CourseEnrollmentDAO {
    private ConnectionHandler connectionHandler;

    private static CourseEnrollmentDAO INSTANCE = new CourseEnrollmentDAOImpl();

    private CourseEnrollmentDAOImpl() {
        this.connectionHandler = ConnectionHandlerImpl.getInstance();
    }

    public static CourseEnrollmentDAO getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertEnrollment(int studentId, int courseId) {
        connectionHandler.getConnection().getTransaction().begin();
        connectionHandler.getConnection().createNativeQuery(
                "INSERT INTO course_enrollments (studentId, courseId) VALUES (?,?)")
                .setParameter(1, studentId)
                .setParameter(2, courseId)
                .executeUpdate();
        connectionHandler.getConnection().getTransaction().commit();
    }

    @Override
    public void deleteEnrollmentBy(int studentId, int courseId) {
        connectionHandler.getConnection().getTransaction().begin();

        Student student = connectionHandler.getConnection().find(Student.class, studentId);
        Course course = connectionHandler.getConnection().find(Course.class, studentId);
        CourseEnrollment courseEnrollmentToDelete = connectionHandler.getConnection().find(CourseEnrollment.class, new CourseEnrollmentId(course, student));
        connectionHandler.getConnection().remove(courseEnrollmentToDelete);

        connectionHandler.getConnection().getTransaction().commit();
    }

    @Override
    public List<Student> retrieveStudentsEnrolledToACourseTaughtBy(int professorId) {
        List<Student> result = new ArrayList<>();

        Query query = connectionHandler.getConnection().createQuery(
                """
                      SELECT ce.id.student
                      FROM CourseEnrollment ce
                      where ce.id.course = (
                        SELECT c
                        FROM Course c
                        WHERE c.professor = :professor
                      )
                   """
        );
        Professor professor = connectionHandler.getConnection().find(Professor.class, professorId);
        query.setParameter("professor", professor);
        result = query.getResultList();

        return result;
    }

    @Override
    public List<Student> retrieveEnrollmentCountByStudent(int numberOfCourses) {
        List<Student> result = new ArrayList<>();

        Query query = connectionHandler.getConnection().createQuery(
                """
                      SELECT ce.id.student
                      FROM CourseEnrollment ce
                      group by ce.id.student
                      having COUNT(ce) = :count
                   """
        );
        query.setParameter("count", numberOfCourses);
        result = query.getResultList();

        return result;
    }

    @Override
    public List<CourseEnrollment> retrieveAll() {
        List<CourseEnrollment> result = new ArrayList<>();

        Query query = connectionHandler.getConnection().createQuery("SELECT c FROM CourseEnrollment c");
        result = query.getResultList();

        return result;
    }
}
