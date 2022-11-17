package com.example.hibernate_exercise.dao.professor;

import com.example.hibernate_exercise.model.Person;
import com.example.hibernate_exercise.model.Professor;

import java.util.List;

public interface ProfessorDAO {
    void insertProfessor(Person person, String subject, int assumption_year);
    void deleteProfessorBy(int id);
    void updateProfessorTaughtSubject(int id, String newSubjectName);
    List<Professor> retrieveWorkingProfessors();
    List<Professor> retrieveFreeProfessors();
    List<Professor> retrieveAll();
}
