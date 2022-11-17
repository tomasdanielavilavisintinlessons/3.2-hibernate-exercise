package com.example.hibernate_exercise.dao.student;

import com.example.hibernate_exercise.model.Person;
import com.example.hibernate_exercise.model.Student;

import java.util.List;

public interface StudentDAO {
    void insertStudent(Person person, int yearOfStudy, int enrollmentYear);
    void deleteStudentBy(int id);
    List<Student> retrieveFreeStudents();
    List<Student> retrieveAll();
}
