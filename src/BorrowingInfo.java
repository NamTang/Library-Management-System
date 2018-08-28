import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class BorrowingInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 162196402529621369L;
	private int codeOfBorrower;
	private List<Book> bookList = new ArrayList<Book>();
	private Date bTime;
	private Date expectRT;
	private Date actualRT;

	public BorrowingInfo(int cbr, Book b, int bD) {
		Date bT = new Date();
		setCodeOfBorrower(cbr);
		addBook(b);
		setbTime(bT);
		setExpectRT(addDays(bTime, bD));
		// setActualRT(addDays(bTime, (bD + 4)));
	}

	public BorrowingInfo(int cbr, Book b, int bD, Date aRT) {
		Date bT = new Date();
		setCodeOfBorrower(cbr);
		addBook(b);
		setbTime(bT);
		setExpectRT(addDays(bTime, bD));
		setActualRT(aRT);
	}

	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (isReturned() == false) {
			return String.format("%-5d| %-15s| %-15s", codeOfBorrower, df.format(getbTime()), df.format(getExpectRT()));
		}
		return String.format("%-5d| %-15s| %-15s| %-15s", codeOfBorrower, df.format(getbTime()),
				df.format(getExpectRT()), df.format(getActualRT()));
	}

	public void addBook(Book b) {
		bookList.add(b);
	}

	public boolean isReturned() {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			df.format(getActualRT());
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}

	private static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public int getCodeOfBorrower() {
		return codeOfBorrower;
	}

	public void setCodeOfBorrower(int codeOfBorrower) {
		this.codeOfBorrower = codeOfBorrower;
	}

	public Date getbTime() {
		return bTime;
	}

	public void setbTime(Date bTime) {
		this.bTime = bTime;
	}

	public Date getExpectRT() {
		return expectRT;
	}

	public void setExpectRT(Date expectRT) {
		this.expectRT = expectRT;
	}

	public Date getActualRT() {
		return actualRT;
	}

	public void setActualRT(Date actualRT) {
		this.actualRT = actualRT;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

}
