package com.sms.service;

import java.sql.SQLException;
import java.util.List;

import com.sms.module.Student;

public interface StudentService {

	boolean addStudent(Student student) throws SQLException;

	List<Student> getAllStudents()throws SQLException;

	Student getStudentById(int id)throws SQLException;

	boolean updateStudent(int id, Student student)throws SQLException;

	boolean deleteStudent(int id)throws SQLException;
}
