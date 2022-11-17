package com.example.hibernate_exercise.dao.course;

import com.example.hibernate_exercise.model.Course;

import java.util.List;

public interface CourseDAO {
    void insertCourse(String courseName, int professorId);
    void deleteCourseBy(int id);
    void updateCourseName(int id, String courseNewName);
    List<Course> retrieveAll();
}