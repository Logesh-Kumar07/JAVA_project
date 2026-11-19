package com.sms;

import java.sql.SQLException;

import com.sms.Repository.StudentRepositoryImpl;
import com.sms.controller.StudentController;
import com.sms.service.StudentService;
import com.sms.service.StudentServiceImpl;
import com.sms.view.StudentView;

public class Main {
	public static void main(String[] args) throws SQLException {
		StudentRepositoryImpl repository = new StudentRepositoryImpl();
		
		StudentService service = new StudentServiceImpl(repository);
		
		StudentController controller = new StudentController(service);
		
		StudentView view = new StudentView(controller);
		view.start();
	}
}
