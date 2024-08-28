package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.poi.EncryptedDocumentException;

import data.Csv;
import data.Excel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController extends Controller implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private TextField group;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
	public Text error_msg;
    @FXML
    public ListView<String> studentsView;
 
    String enteredName, enteredSurname;
    int enteredGroup;
    
    List list = new List();
    
    public StringBuilder sb;
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(int i = 1; i <= List.noOfRows + List.studentsAdded; ++i) {
			sb = new StringBuilder();
			sb.append(String.format(List.studentList.get(i-1).getName() + " " + List.studentList.get(i-1).getSurname() + " " + List.studentList.get(i-1).getGroup()));
			studentsView.getItems().add(sb.toString());
		}
		
		studentsView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				int index = studentsView.getSelectionModel().getSelectedIndex();
				
				name.setText(List.studentList.get(index).getName());
				surname.setText(List.studentList.get(index).getSurname());
				group.setText(Integer.toString(List.studentList.get(index).getGroup()));
			}
			
		});
	}
	
	public void addStudent(ActionEvent event) {
		try {
			if(!name.getText().isEmpty() && !surname.getText().isEmpty()) {
				enteredName = name.getText();
				enteredSurname = surname.getText();
				enteredGroup = Integer.parseInt(group.getText());
				name.clear();
				surname.clear();
				group.clear();
				
				list.addStudentToList(enteredName, enteredSurname, enteredGroup);
				
				error_msg.setText(enteredName+" sëkmingai pridëtas (-a)");
				
			} else {
				error_msg.setText("Uþpildykite bûtinus laukus!");
			}
		}  catch (NumberFormatException e) {
			error_msg.setText("Grupë turi bûti skaièius!");
			System.out.println(e);
		} catch (Exception e) {
			error_msg.setText("Nenumatyta klaida");
			System.out.println(e);
		}
		
		reloadList();
	}
	
	public void loadFromExcel(ActionEvent event) {
		Excel excel = new Excel();
		try {
			excel.readFromFile();
			error_msg.setText("Sëkmingai nuskaityta ið failo");
		} catch(FileNotFoundException e) {
			error_msg.setText("Nëra failo, ið kurio bûtø galima skaityti");
			e.printStackTrace();
		} catch (Exception e) {
			error_msg.setText("Failo klaida");
			e.printStackTrace();
		}
		
		reloadList();
	}
	
	public void loadFromCSV(ActionEvent event) throws EncryptedDocumentException, IOException {
		Csv csv = new Csv();
		try {
			csv.readFromFile();
			error_msg.setText("Sëkmingai nuskaityta ið failo");
		} catch(FileNotFoundException e) {
			error_msg.setText("Nëra failo, ið kurio bûtø galima skaityti");
			e.printStackTrace();
		} catch (Exception e) {
			error_msg.setText("Nëra failo, ið kurio bûtø galima skaityti");
			e.printStackTrace();
		}
		
		reloadList();
	}
	
	public void updateExcel(ActionEvent event) throws EncryptedDocumentException, IOException {
		Excel excel = new Excel();
		error_msg.setText("Iðsaugota á faila");
		excel.saveToFile(List.studentList);
	}
	
	public void updateCSV(ActionEvent event) throws IOException {
		Csv csv = new Csv();
		error_msg.setText("Iðsaugota á faila");
		csv.saveToFile(List.studentList);
	}
	
	public void reloadList() {
		studentsView.getItems().clear();
		
		for (int i = 1; i <= List.noOfRows+List.studentsAdded; ++i) {
			sb = new StringBuilder();
			sb.append(String.format(List.studentList.get(i-1).getName() + " " + List.studentList.get(i-1).getSurname() + " " + List.studentList.get(i-1).getGroup()));
			studentsView.getItems().add(sb.toString());
		}
	}
	
	public void goToAttendanceFill(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AttendanceFill.fxml"));
		root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void goToAttendanceFilter(ActionEvent event) throws IOException  {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AttendanceFilter.fxml"));
		root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void goToMain(ActionEvent event) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	public void editStudent(ActionEvent event) {
		int index = studentsView.getSelectionModel().getSelectedIndex();
		
		if (index >= 0) {
			if(!name.getText().isEmpty()) {
				List.studentList.get(index).changeName(name.getText());
			}
			
			if(!surname.getText().isEmpty()) {
				List.studentList.get(index).changeSurname(surname.getText());
			}
			
			if(!group.getText().isEmpty()) {
				List.studentList.get(index).changeGroup(Integer.parseInt(group.getText()));
			}
			
			error_msg.setText("Studentas paredaguotas sëkmingai!");
			
			reloadList();
		} else {
			error_msg.setText("Pasirinkite studentà, kurá norite redaguoti!");
		}
	}

}
