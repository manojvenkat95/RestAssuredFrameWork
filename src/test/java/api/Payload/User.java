package api.Payload;

public class User {
	String name;
	String Location;
	String Phone;
	int yearsOld;
	String Courses[];
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getPhone() {
		return Phone;
	}
	public void setPhone(String phone) {
		Phone = phone;
	}
	public int getYearsOld() {
		return yearsOld;
	}
	public void setYearsOld(int yearsOld) {
		this.yearsOld = yearsOld;
	}
	public String[] getCourses() {
		return Courses;
	}
	public void setCourses(String[] courses) {
		Courses = courses;
	}
	
}
