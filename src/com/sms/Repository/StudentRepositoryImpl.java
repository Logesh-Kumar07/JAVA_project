package com.sms.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sms.module.Student;
import com.sms.util.DBConnection;

public class StudentRepositoryImpl implements StudentRepository{

	private ResultSet executeQuary;

	@Override
	public boolean add(Student student) throws SQLException {
		String sql = "INSERT INTO students" + "(Student_name, email, departement, phone)" +"VALUES(?,?,?,?)";
		try(Connection com = DBConnection.getConnection();
				PreparedStatement ps = com.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getDepartment());
			ps.setString(4, student.getPhoneNumber());
			int rows = ps.executeUpdate();
			
			if(rows == 1) {
				try(ResultSet keys = ps.getGeneratedKeys()){
					if(keys.next()) {
						student.setStudentid(keys.getInt(1));
						return false;
					}
				}
			}
			
		}
		return false;
	}

	@Override
	public List<Student> finalAll() throws SQLException {
		List<Student> list = new ArrayList<>();
		String sql ="SELECT * FROM students";
		try(Connection com = DBConnection.getConnection();
				PreparedStatement ps = com.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					list.add(mapRow(rs));
				}
		}
		return list;
	}

	private Student mapRow(ResultSet rs) throws SQLException{
		
		return new Student(rs.getInt("studentid"), rs.getString("student_name"), rs.getString("email"), rs.getString("departement"), rs.getString("phone"));
		
	}

	@Override
	public Student finalById(int id) throws SQLException {
		String sql = "SELECT FROM students WHERE studentid=?";
		try(Connection com = DBConnection.getConnection();
				PreparedStatement ps = com.prepareStatement(sql)){
			
			ps.setInt(1, id);
			try(ResultSet rs= ps.executeQuery()){
				return rs.next() ? mapRow(rs) : null;
				
			}
		}
	}

	@Override
	public boolean update(int id, Student student) throws SQLException {
		String sql = "UPATE student SET student_name =?, email=?,depertment=?,phone=? WHERE studentid=?";
		try(Connection com = DBConnection.getConnection();
				PreparedStatement ps = com.prepareStatement(sql)){
			ps.setString(1, student.getStudentName());
			ps.setString(2, student.getEmail());
			ps.setString(3, student.getDepartment());
			ps.setString(4, student.getPhoneNumber());
			ps.setString(5, sql);
			return ps.executeUpdate() == 1;
		}

	}

	@Override
	public boolean delate(int id) throws SQLException {
		String sql = "DELETE FROM students WHERE studentid=?";
		try(Connection com = DBConnection.getConnection();
				PreparedStatement ps = com.prepareStatement(sql)){
			
			ps.setInt(1, id);
			return ps.executeUpdate() == 1;
		}

	
	}

}
