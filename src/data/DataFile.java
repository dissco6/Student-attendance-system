package data;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;

import application.Student;

public interface DataFile {
	public void readFromFile()throws IOException;
	public void saveToFile(ArrayList<Student> studentList) throws IOException;
}
