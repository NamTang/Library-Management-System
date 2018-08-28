import java.io.Serializable;

public class Author implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String fullName;
	private int yearOfBirth;
	private int yearOfDeath;
	private String introduce;

	public Author(int c, String n, int b, int d, String intro) {
		setCode(c);
		setFullName(n);
		setYearOfBirth(b);
		setYearOfDeath(d);
		setIntroduce(intro);
	}

	public String toString() {
		if (isAlive()) {
			return String.format("%-5d| %-30s| %-11d| %-11s| %s", code, fullName, yearOfBirth, "Alive", introduce);
		} else {
			return String.format("%-5d| %-30s| %-11d| %-11d| %s", code, fullName, yearOfBirth, yearOfDeath, introduce);
		}
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

	public int getYearOfDeath() {
		return yearOfDeath;
	}

	public void setYearOfDeath(int yearOfDeath) {
		this.yearOfDeath = yearOfDeath;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public boolean isAlive() {
		if (this.yearOfDeath - this.yearOfBirth < 0) {
			return true;
		} else {
			return false;
		}

	}
}
