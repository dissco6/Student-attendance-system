package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import data.Pdf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AttendanceFilterController extends Controller implements Initializable {
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Text error_msg;
    @FXML
    private DatePicker date;
    @FXML
    public ListView<String> studentsView;
    
    public StringBuilder sb;
    private String filledDate;

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

	@Override
	public void goToAttendanceFill(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AttendanceFill.fxml"));
		root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}

	@Override
	public void goToAttendanceFilter(ActionEvent event) throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void goToMain(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
		root = loader.load();
		
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
    void Filter(ActionEvent event) {
		StringBuilder sb;
		filledDate = date.getValue().toString();
		int found = 0;
		
		studentsView.getItems().clear();
		
		for(int i = 0; i < List.noOfRows + List.studentsAdded; ++i) {
			int j = 0;
			for(Attendance a : List.studentList.get(i).attendanceArray) {
				if(List.studentList.get(i).attendanceArray.get(j).getDate().equals(filledDate)) {
					sb = new StringBuilder();
					sb.append(String.format(List.studentList.get(i).getName() + " " + List.studentList.get(i).getSurname() + " " + List.studentList.get(i).getGroup() + " " + filledDate + " " + List.studentList.get(i).attendanceArray.get(j).getIfAttended()));
					studentsView.getItems().add(sb.toString());
					++found;
				}
				++j;
			}
		}
		
		if(found == 0) {
			error_msg.setText("Apie studentø lankomumà "+filledDate+" nëra duomenø!");
		} else {
			error_msg.setText("");
		}
    }

    @FXML
    void downloadAttendanceSheet(ActionEvent event) throws FileNotFoundException {
    	Pdf pdf = new Pdf();
		pdf.printReport(filledDate);
    }

}
