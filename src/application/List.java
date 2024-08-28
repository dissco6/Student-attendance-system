package application;

import java.util.ArrayList;

public class List {
	public static ArrayList<Student> studentList = new ArrayList<Student>();
	public static int studentsAdded;
	public static int noOfRows;
	private Student student;
	
	public void addStudentToList(String name, String surname, int group) {
		student = new Student(name, surname, group);
		studentList.add(student);
		
		++studentsAdded;
	}
}
