package com.example.hibernate_exercise.service.course;

import com.example.hibernate_exercise.model.Course;

import java.util.List;

public interface CourseService {
    void insertCourse(String courseName, int professorId);
    void deleteCourseBy(int id);
    void updateCourseName(int id, String courseNewName);
    List<Course> retrieveAll();
}
