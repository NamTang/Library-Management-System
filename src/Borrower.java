import java.io.Serializable;

public class Borrower implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6386540577424931944L;
	private int code;
	private String fullName;
	private int id;
	private int yearOfBirth;
	private Department department;

	public Borrower(int c, String n, int id, int y, Department d) {
		setCode(c);
		setFullName(n);
		setId(id);
		setYearOfBirth(y);
		setDepartment(d);
	}

	public String toString() {
		return String.format("%-5s| %-20s| %-15d| %-11d| %s", code, fullName, id, yearOfBirth,
				department.getShortName());
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
}
