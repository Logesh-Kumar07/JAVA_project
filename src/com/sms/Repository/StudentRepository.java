package com.sms.Repository;

import java.sql.SQLException;

import java.util.List;

import com.sms.module.Student;

public interface StudentRepository {
	boolean add(Student student) throws SQLException;

	List<Student> finalAll() throws SQLException;

	Student finalById(int id) throws SQLException;

	boolean update(int id, Student student) throws SQLException;

	boolean delate(int id) throws SQLException;

}
