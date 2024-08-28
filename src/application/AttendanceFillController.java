package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AttendanceFillController extends Controller implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
    @FXML
    private RadioButton was, wasNot;
    @FXML
    private DatePicker date;
    @FXML
    private Text error_msg;
    @FXML
    private ListView<String> studentsView;
    
    public StringBuilder sb;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for(int i = 1; i <= List.noOfRows + List.studentsAdded; ++i) {
			sb = new StringBuilder();
			sb.append(String.format(List.studentList.get(i-1).getName() + " " + List.studentList.get(i-1).getSurname() + " " + List.studentList.get(i-1).getGroup()));
			studentsView.getItems().add(sb.toString());
		}
		
		date.setValue(LocalDate.now());
	}
	
	@FXML
	public void goToMain(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void goToAttendanceFilter(ActionEvent event) throws IOException  {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AttendanceFilter.fxml"));
		root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void goToAttendanceFill(ActionEvent event) throws IOException {
		
	}
	
	@FXML
    public void mark(ActionEvent event) {
		String filledDate = date.getValue().toString();
		
		int i = 0, changed = 0;
		
		for(Attendance j : List.studentList.get(studentsView.getSelectionModel().getSelectedIndex()).attendanceArray) {
			if(List.studentList.get(studentsView.getSelectionModel().getSelectedIndex()).attendanceArray.get(i).getDate().equals(filledDate)) {
				if(was.isSelected()) {
					List.studentList.get(studentsView.getSelectionModel().getSelectedIndex()).attendanceArray.get(i).change(true);
					++changed;
				} else if(wasNot.isSelected()) {
					List.studentList.get(studentsView.getSelectionModel().getSelectedIndex()).attendanceArray.get(i).change(false);
					++changed;
				}
			}
			
			++i;
		}
		
		if (changed == 0) {
			if(was.isSelected()) {
				List.studentList.get(studentsView.getSelectionModel().getSelectedIndex()).fillAttendance(filledDate, true);
			} else if(wasNot.isSelected()) {
				List.studentList.get(studentsView.getSelectionModel().getSelectedIndex()).fillAttendance(filledDate, false);
			}
		}
		
		error_msg.setText(List.studentList.get(studentsView.getSelectionModel().getSelectedIndex()).getName() +" lankomumas uþpildytas");
	}

}
