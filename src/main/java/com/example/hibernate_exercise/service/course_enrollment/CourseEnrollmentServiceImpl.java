package com.example.hibernate_exercise.service.course_enrollment;

import com.example.hibernate_exercise.dao.course_enrollment.CourseEnrollmentDAO;
import com.example.hibernate_exercise.dao.course_enrollment.CourseEnrollmentDAOImpl;
import com.example.hibernate_exercise.model.CourseEnrollment;
import com.example.hibernate_exercise.model.Student;

import java.util.List;

public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {
    private CourseEnrollmentDAO courseEnrollmentDAO;

    private static CourseEnrollmentService INSTANCE = new CourseEnrollmentServiceImpl();

    private CourseEnrollmentServiceImpl() {
        this.courseEnrollmentDAO = CourseEnrollmentDAOImpl.getInstance();
    }

    public static CourseEnrollmentService getInstance() {
        return INSTANCE;
    }

    @Override
    public void insertEnrollment(int studentId, int courseId) {
        courseEnrollmentDAO.insertEnrollment(studentId, courseId);
    }

    @Override
    public void deleteEnrollmentBy(int studentId, int courseId) {
        courseEnrollmentDAO.deleteEnrollmentBy(studentId, courseId);
    }

    @Override
    public List<Student> retrieveStudentsEnrolledToACourseTaughtBy(int professorId) {
        return courseEnrollmentDAO.retrieveStudentsEnrolledToACourseTaughtBy(professorId);
    }

    @Override
    public List<Student> retrieveEnrollmentCountByStudent(int numberOfCourses) {
        return courseEnrollmentDAO.retrieveEnrollmentCountByStudent(numberOfCourses);
    }

    @Override
    public List<CourseEnrollment> retrieveAll() {
        return courseEnrollmentDAO.retrieveAll();
    }
}
