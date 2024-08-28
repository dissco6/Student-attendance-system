package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import application.List;
import application.Student;

public class Excel extends List implements DataFile {
	private static Workbook workbook;
	private static Sheet sheet;
	private static FileInputStream input;
	private static FileOutputStream output;
	private static Row row;
	private static Cell cellName, cellSurname, cellGroup;
	
	private Student student;
	
	@Override
	public void readFromFile() throws EncryptedDocumentException, IOException {
		input = new FileInputStream("data/studentList.xlsx");
		workbook = WorkbookFactory.create(input);
		sheet = workbook.getSheet("Sheet1");
		noOfRows = sheet.getLastRowNum();
		
		for(int i = 1; i <= noOfRows; ++i) {
			student = new Student(sheet.getRow(i).getCell(0).toString(), sheet.getRow(i).getCell(1).toString(), (int)Double.parseDouble(sheet.getRow(i).getCell(2).toString()));
			studentList.add(student);
		}

	}
	
	@Override
	public void saveToFile(ArrayList<Student> studentList) throws EncryptedDocumentException, IOException {
		for(int i = 1; i <= List.noOfRows + List.studentsAdded; ++i) {
			row = sheet.createRow(i);
			cellName = row.createCell(0);
			cellSurname = row.createCell(1);
			cellGroup = row.createCell(2);
			
			cellName.setCellValue(studentList.get(i-1).getName());
			cellSurname.setCellValue(studentList.get(i-1).getSurname());
			cellGroup.setCellValue(studentList.get(i-1).getGroup());
			output = new FileOutputStream("data/studentList.xlsx");
			workbook.write(output);
		}
		output.flush();
		output.close();
	}
}
