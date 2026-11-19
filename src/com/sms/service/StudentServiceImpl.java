package com.sms.service;

import java.sql.SQLException;
import java.util.List;

import com.sms.Repository.StudentRepository;

import com.sms.module.Student;

public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;
	

	public StudentServiceImpl(StudentRepository studentRepository) {

		this.studentRepository = studentRepository;
	}
	private void validate(Student s) {
		if(s == null || s.getStudentName() == null || s.getStudentName().isBlank() || 
				s.getEmail() == null || !s.getEmail().matches("`[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$`") ||
				s.getDepartment() == null ||s.getDepartment().isBlank() || 
				!s.getPhoneNumber().matches("`[0-9]{10,15}`") ) {
			throw new IllegalArgumentException("Enter valid student details.");
		}
	}

	@Override
	public boolean addStudent(Student student) throws SQLException{
		
		validate(student);
		return studentRepository.add(student);
	}

	@Override
	public List<Student> getAllStudents() throws SQLException{

		return studentRepository.finalAll();
	}

	@Override
	public Student getStudentById(int id) throws SQLException{
		if(id <= 0) {
			throw new IllegalArgumentException("ID must be position.");
		}
		return studentRepository.finalById(id);
	}

	@Override
	public boolean updateStudent(int id, Student student) throws SQLException {
	
		validate(student);
		return studentRepository.update(id, student);
	}

	@Override
	public boolean deleteStudent(int id) throws SQLException {
		
		if(id <= 0) {
			throw new IllegalArgumentException("ID must be position.");
		}
		return studentRepository.delate(id);
	}

}
