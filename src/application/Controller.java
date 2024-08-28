package application;

import java.io.IOException;

import javafx.event.ActionEvent;

public abstract class Controller {
	public abstract void goToAttendanceFill(ActionEvent event) throws IOException;
	public abstract void goToAttendanceFilter(ActionEvent event) throws IOException;
	public abstract void goToMain(ActionEvent event) throws IOException;
}
