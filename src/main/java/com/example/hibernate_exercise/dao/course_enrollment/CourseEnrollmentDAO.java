package com.example.hibernate_exercise.dao.course_enrollment;

import com.example.hibernate_exercise.model.CourseEnrollment;
import com.example.hibernate_exercise.model.Student;

import java.util.List;

public interface CourseEnrollmentDAO {
    void insertEnrollment(int studentId, int courseId);
    void deleteEnrollmentBy(int studentId, int courseId);
    List<Student> retrieveStudentsEnrolledToACourseTaughtBy(int professorId);
    List<Student> retrieveEnrollmentCountByStudent(int numberOfCourses);
    List<CourseEnrollment> retrieveAll();
}
