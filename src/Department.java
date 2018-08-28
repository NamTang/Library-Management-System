import java.io.Serializable;

public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4580053166673250398L;
	private int code;
	private String name;
	private String shortName;

	public Department(int c, String n, String sn) {
		setCode(c);
		setName(n);
		setShortName(sn);
	}

	public String toString() {
		return String.format("%-5s| %-10s| %-20s", code, shortName, name);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

}
