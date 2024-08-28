package application;

public class Attendance {
	String date;
	boolean attended;
	
	public void fill(String date, boolean attended) {
		this.date = date;
		this.attended = attended;
	}
	
	public void change(boolean attended) {
		this.attended = attended;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getIfAttended() {
		if(attended == true) {
			return "Buvo";
		} else {	
			return "Nebuvo";
		}
	}
}
