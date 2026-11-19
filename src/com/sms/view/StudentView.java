package com.sms.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.sms.controller.StudentController;
import com.sms.module.Student;

public class StudentView {
	private final StudentController controller;
	private final Scanner sc =new Scanner(System.in);
	public StudentView(StudentController controller) {
		
		this.controller = controller;
	}
	private Student readStudent(int id) {
		System.out.print("Student name");
		String name = sc.nextLine();
		
		System.out.print("Email");
		String email = sc.nextLine();
		
		System.out.print("Department:");
		String department = sc.nextLine();
		
		System.out.print("Phone (10-15 digits):");
		String phone = sc.nextLine();
		return new Student(id, name, email, department, phone);
	}
	
	public void start() throws SQLException{
		while(true) {
			try {
				System.out.println("\n 1.Add 2.viwe 3.Search 4.update 5.delete 6.exit");
				
				System.out.println("choose: ");
				int choice = Integer.parseInt(sc.nextLine());
				
				switch(choice){
				case 1:
					System.out.println("Syudent ID: ");
					int newId = Integer.parseInt(sc.nextLine());
					
					System.out.println(controller.add(readStudent(newId)) ? "Student added." : "ID/Email Already exits");
					break;
				case 2:
					controller.getAll().forEach(System.out::println);
					if(controller.getAll().isEmpty()) {
						System.out.println("no student found.");
					}
					break;
				case 3:
					System.out.println("Student ID:");
					Student found = controller.getById(Integer.parseInt(sc.nextLine()));
					System.out.println(found == null ? "Student not found." : found);
					break;
				case 4:
					System.out.println("ID to update: ");
					int updateID = Integer.parseInt(sc.nextLine());
					
					System.out.println(controller.update(updateID, readStudent(updateID)) ? "Student update" : "student not found or email is already exits.");
					break;
				case 5:
					System.out.println("ID to delete: ");
					System.out.println(controller.delete(Integer.parseInt(sc.nextLine())) ? "Student deleted. " : "student not found.");
					break;
				case 0:
					System.out.println("Thank you..");
					return;
				default:
					System.out.println("Choose a valid option.");
					
				}
			}catch(NumberFormatException e){
				System.out.println("Enter a valid number:");
			}catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
