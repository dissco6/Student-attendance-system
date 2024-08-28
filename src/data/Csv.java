package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVWriter;

import application.List;
import application.Student;

public class Csv extends List implements DataFile {
	
	@Override
	public void readFromFile() throws IOException {
		String file = "data/studentListCSV.csv";
		BufferedReader reader = null;
		String line = "";
		Student student;
		
		try {
			reader = new BufferedReader(new FileReader(file));
			int isNew = 0;
			
			while((line = reader.readLine()) != null) {
				
				if(isNew != 0) {
					String row[] = line.split(";");
					++studentsAdded;
					
					student = new Student(row[0], row[1], Integer.parseInt(row[2]));
					List.studentList.add(student);
					System.out.println();
				} else {
					++isNew;
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
		
	}

	@Override
	public void saveToFile(ArrayList<Student> studentList) throws IOException {
		File file = new File("data/studentListCSV.csv");
	    try {
	        FileWriter outputfile = new FileWriter(file);
	        CSVWriter writer = new CSVWriter(outputfile, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
	  
	        String[] header = { "Name", "Surname", "Group" };
	        writer.writeNext(header);
	        
	        for(int i = 1; i <= List.noOfRows + List.studentsAdded; ++i) {
	        	String data[] = {studentList.get(i-1).getName(), studentList.get(i-1).getSurname(), Integer.toString(studentList.get(i-1).getGroup()) };
	        	writer.writeNext(data);
	        }
	  
	        writer.close();
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
}