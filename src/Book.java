
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9063939802261588622L;
	private int code;
	private String title;
	private String republish;
	private int publishYear;
	private String binCode;
	private STATUS status;
	List<Author> authorList = new ArrayList<Author>();

	public Book(int c, String t, String r, int p, String bC) {
		setCode(c);
		setTitle(t);
		setRepublish(r);
		setPublishYear(p);
		setBinCode(bC);
		this.status = STATUS.Availavle;

	}

	public Book(int c, String t, String r, int p, String bC, Author a) {
		setCode(c);
		setTitle(t);
		setRepublish(r);
		setPublishYear(p);
		setBinCode(bC);
		addAuthor(a);
		this.status = STATUS.Availavle;
	}

	public Book(int c, String t, String r, int p, String bC, STATUS s) {
		setCode(c);
		setTitle(t);
		setRepublish(r);
		setPublishYear(p);
		setBinCode(bC);
		setStatus(s);

	}

	public String toString() {
		String authors = "";
		for (Author a : authorList) {
			authors = authors + a.getFullName() + ", ";
		}
		return String.format("%-5d| %-30s| %-30s| %-15s| %-15d| %-10s| %-10s", code, title, authors, republish,
				publishYear, binCode, status);
	}

	public void addAuthor(Author a) {
		authorList.add(a);
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRepublish() {
		return republish;
	}

	public void setRepublish(String republish) {
		this.republish = republish;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public String getBinCode() {
		return binCode;
	}

	public void setBinCode(String binCode) {
		this.binCode = binCode;
	}

	public STATUS getStatus() {
		return status;
	}

	public void setStatus(STATUS status) {
		this.status = status;
	}

}
