package application;

import java.util.ArrayList;

public class Student {
	String name;
	String surname;
	int group;
	public ArrayList<Attendance> attendanceArray = new ArrayList<Attendance>();
	
	public Student(String name, String surname, int group){
		this.name = name;
		this.surname = surname;
		this.group = group;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public int getGroup() {
		return group;
	}
	
	public void changeName(String name) {
		this.name = name;
	}
	
	public void changeSurname(String surname) {
		this.surname = surname;
	}
	
	public void changeGroup(int group) {
		this.group = group;
	}
	
	public void fillAttendance(String date, boolean attended) {
		Attendance attendance = new Attendance();
		
		attendance.fill(date, attended);
		
		attendanceArray.add(attendance);
	}
}
