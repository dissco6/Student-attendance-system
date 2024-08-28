package data;

import java.io.FileNotFoundException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import application.Attendance;
import application.List;

public class Pdf {
	
	public void printReport(String date) throws FileNotFoundException {
		StringBuilder sb;
		String path = "data/attendanceReport.pdf";
		PdfWriter writer = new PdfWriter(path);
		
		PdfDocument pdfDocument = new PdfDocument(writer);
		pdfDocument.addNewPage();
		
		Document document = new Document(pdfDocument);
		
		float columnWidth[] = {200f, 50f, 100f};
		Table table = new Table(columnWidth);
		
		table.addCell(new Cell().add(new Paragraph("Studentas")));
		table.addCell(new Cell().add(new Paragraph("Grupë")));
		table.addCell(new Cell().add(new Paragraph(date)));
		
		for(int i = 0; i < List.noOfRows + List.studentsAdded; ++i) {
			int j = 0;
			for(Attendance a : List.studentList.get(i).attendanceArray) {
				if(List.studentList.get(i).attendanceArray.get(j).getDate().equals(date)) {
					table.addCell(new Cell().add(new Paragraph(List.studentList.get(i).getName() + " " + List.studentList.get(i).getSurname())));
					table.addCell(new Cell().add(new Paragraph(Integer.toString(List.studentList.get(i).getGroup()))));
					table.addCell(new Cell().add(new Paragraph(List.studentList.get(i).attendanceArray.get(j).getIfAttended())));
				}
				++j;
			}
		}
		
		document.add(table);
		
		document.close();
	}
}
