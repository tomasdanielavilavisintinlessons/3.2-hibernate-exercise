package com.example.hibernate_exercise.service.student;

import com.example.hibernate_exercise.model.Student;

import java.util.List;

public interface StudentService {
    void insertStudent(String name, String surname, int age,
                       int yearOfStudy, int enrollmentYear);
    void deleteStudentBy(int id);
    List<Student> retrieveFreeStudents();
    List<Student> retrieveAll();
}
