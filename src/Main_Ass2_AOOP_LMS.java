
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main_Ass2_AOOP_LMS {

	static List<Book> bookList = new ArrayList<Book>();
	static List<Author> authorList = new ArrayList<Author>();
	static List<Borrower> borrowerList = new ArrayList<Borrower>();
	static List<BorrowingInfo> borrowingInfoList = new ArrayList<BorrowingInfo>();
	static List<Department> departmentList = new ArrayList<Department>();
	static Scanner sc = new Scanner(System.in);
	static ReaderWriter rw = new ReaderWriter();
	static final String BOOK_FILE_NAME = "book.txt";
	static final String AUTHOR_FILE_NAME = "author.txt";
	static final String BORROWER_FILE_NAME = "borrower.txt";
	static final String BORROWINGINFO_FILE_NAME = "borrowinginfo.txt";
	static final String DEPARTMENT_FILE_NAME = "department.txt";
	// ***************DUMP DATA
	// METHODS************************************************************************************************/

	static void dumpData() {

		// authorList.add(new Author(0, "Herbert Schildt", 1951, 0,
		// "Herbert Schildt is an American computing author, programmer and musician. He
		// has written books about the C and Java programming languages. He was also a
		// founding member of the progressive rock band Starcastle."));
		// authorList.add(new Author(1, "Kathy Sierra", 1957, 0,
		// "Kathy Sierra is an American programming instructor and game developer."));
		//
		// bookList.add(new Book(0, "Java: A Beginner's Guide", "Sixth Edition", 2014,
		// "S1R2", STATUS.Borrowed));
		// bookList.add(new Book(1, "Head First Java", "Second Edition", 2005, "S1R3",
		// STATUS.Borrowed));
		// bookList.get(0).addAuthor(authorList.get(0));
		// bookList.get(1).addAuthor(authorList.get(1));
		//
		// departmentList.add(new Department(0, "JAVA HCM", "JHCM"));
		// departmentList.add(new Department(1, ".NET HCM", ".NHCM"));
		//
		// borrowerList.add(new Borrower(0, "Tang Hoai Nam", 1411061634, 1996,
		// departmentList.get(0)));
		// borrowerList.add(new Borrower(1, "LuCas Waston", 1411061635, 1996,
		// departmentList.get(1)));
		//
		// borrowingInfoList.add(new BorrowingInfo(0, bookList.get(0), 10));
		// borrowingInfoList.add(new BorrowingInfo(1, bookList.get(1), 5));
		//
		// rw.writeToFile(AUTHOR_FILE_NAME, authorList);
		// rw.writeToFile(BOOK_FILE_NAME, bookList);
		// rw.writeToFile(BORROWER_FILE_NAME, borrowerList);
		// rw.writeToFile(DEPARTMENT_FILE_NAME, departmentList);
		// rw.writeToFile(BORROWINGINFO_FILE_NAME, borrowingInfoList);
		//
		// authorList.clear();
		// bookList.clear();
		// borrowerList.clear();
		// departmentList.clear();
		// borrowingInfoList.clear();

		authorList = rw.readListFromFile(Author.class, AUTHOR_FILE_NAME);
		bookList = rw.readListFromFile(Book.class, BOOK_FILE_NAME);
		departmentList = rw.readListFromFile(Department.class, DEPARTMENT_FILE_NAME);
		borrowerList = rw.readListFromFile(Borrower.class, BORROWER_FILE_NAME);
		borrowingInfoList = rw.readListFromFile(BorrowingInfo.class, BORROWINGINFO_FILE_NAME);
	}

	// ***************END DUMP DATA
	// METHODS************************************************************************************************/
	// ***************CHECK & GET
	// METHODS************************************************************************************************/

	static boolean isLate(BorrowingInfo b) {
		if (b.getActualRT().compareTo(b.getExpectRT()) > 0) {
			return true;
		}
		return false;
	}

	static boolean isDepartmentName(String name) {
		for (Department d : departmentList) {
			if (d.getName().toLowerCase().equals(name.toLowerCase()))
				return true;
		}
		return false;
	}

	static boolean isDepartmentShortName(String sn) {
		for (Department d : departmentList) {
			if (d.getShortName().toLowerCase().equals(sn.toLowerCase()))
				return true;
		}
		return false;
	}

	static boolean isLeapYear(int year) {
		if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}

	static boolean isValidDate(int day, int month, int year) {
		if (!isLeapYear(year)) {
			if (month == 2) {
				if (day > 28) {
					return false;
				}
			}
		}
		return true;
	}

	static int getDepartmentCodeByName(String name) {
		for (Department d : departmentList) {
			if (d.getName().toLowerCase().equals(name.toLowerCase())) {
				return d.getCode();
			}
		}
		return -1;
	}

	static int getDepartmentCodeByShortName(String sn) {
		for (Department d : departmentList) {
			if (d.getShortName().toLowerCase().equals(sn.toLowerCase())) {
				return d.getCode();
			}
		}
		return -1;
	}

	static String getMonthString(int month) {
		return new DateFormatSymbols().getMonths()[month - 1];
	}

	// ***************END CHECK & GET
	// METHODS************************************************************************************************/

	// ***************DISPLAY
	// METHODS************************************************************************************************/

	static void displayAllBook() {
		System.out.println(
				"======================================================================================================================================");
		System.out.printf("%-5s| %-30s| %-30s| %-15s| %-15s| %-10s| %-10s\n", "CODE", "TITLE", "AUTHOR", "REPUBLISH",
				"PUBLISH YEAR", "BIN CODE", "STATUS");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");
		for (Book b : bookList) {
			System.out.println(b.toString());
		}
	}

	static void displayAllAvailableBook() {
		int k = 0;
		System.out.println(
				"======================================================================================================================================");
		System.out.printf("%-5s| %-30s| %-30s| %-15s| %-15s| %-10s| %-10s\n", "CODE", "TITLE", "AUTHOR", "REPUBLISH",
				"PUBLISH YEAR", "BIN CODE", "STATUS");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");
		for (Book b : bookList) {
			if (b.getStatus() != STATUS.Availavle) {
				k++;
			} else if (b.getStatus() == STATUS.Availavle) {
				System.out.println(b.toString());
			}
			if (k == bookList.size()) {
				System.out.println("There is no book available now");
			}
		}
	}

	static void displayAllAuthor() {
		System.out.println(
				"======================================================================================================================================");
		System.out.printf("%-5s| %-30s| %-11s| %-11s| %s\n", "CODE", "FULL NAME", "BIRTH YEAR", "DEATH YEAR",
				"INTRODUCE");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");
		for (Author a : authorList) {
			System.out.println(a.toString());
		}
	}

	static void displayAliveAuthor() {
		int k = 0;
		System.out.println(
				"======================================================================================================================================");
		System.out.printf("%-5s| %-30s| %-11s| %-11s| %s\n", "CODE", "FULL NAME", "BIRTH YEAR", "DEATH YEAR",
				"INTRODUCE");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");
		for (Author a : authorList) {
			if (!a.isAlive()) {
				k++;
			} else {
				System.out.println(a.toString());
			}
			if (k == authorList.size()) {
				System.out.println("No one is alive.");
			}

		}
	}

	static void displayAllBorrower() {
		System.out.println(
				"======================================================================================================================================");
		System.out.printf("%-5s| %-20s| %-15s| %-11s| %s\n", "CODE", "FULL NAME", "ID", "BIRTH YEAR", "DEPARTMENT");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");

		for (Borrower b : borrowerList) {
			System.out.println(b.toString());
		}
	}

	static void displayAllBorrowingInfo() {
		System.out.println(
				"======================================================================================================================================");
		System.out.printf("%-5s| %-15s| %-15s| %-15s\n", "COB", "BORROWING TIME", "EXPECT RETURN", "ACTUAL RETURN");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");

		for (BorrowingInfo b : borrowingInfoList) {
			System.out.println(b.toString());
			System.out.println("List of borrowing books");
			System.out.println(
					"--------------------------------------------------------------------------------------------------------------------------------------");

			for (Book book : b.getBookList()) {
				System.out.printf("%-5d| %-20s\n", book.getCode(), book.getTitle());
			}
			System.out.println(
					"*************************************************************************************************************************************");

		}
	}

	static void displayBorrowingInfoWhichIsNotReturned() {
		int k = 0;
		System.out.println(
				"======================================================================================================================================");
		System.out.printf("%-5s| %-15s| %-15s| %-15s\n", "COB", "BORROWING TIME", "EXPECT RETURN", "ACTUAL RETURN");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");

		for (BorrowingInfo b : borrowingInfoList) {
			if (b.isReturned()) {
				k++;
			} else {
				System.out.println(b.toString());
				System.out.println("List of borrowing books");
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------------");

				for (Book book : b.getBookList()) {
					System.out.printf("%-5d| %-20s\n", book.getCode(), book.getTitle());
				}
				System.out.println(
						"*************************************************************************************************************************************");
			}
			if (borrowingInfoList.size() == k) {
				System.out.println("There is no Borrowing Infomation");
			}
		}
	}

	static void displayBorrowingInfoWhichIsLateReturned() {
		int k = 0;
		System.out.println(
				"======================================================================================================================================");
		System.out.printf("%-5s| %-15s| %-15s| %-15s\n", "COB", "BORROWING TIME", "EXPECT RETURN", "ACTUAL RETURN");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");

		for (BorrowingInfo bi : borrowingInfoList) {
			if (bi.isReturned() && isLate(bi)) {
				System.out.println(bi.toString());
				System.out.println("List of borrowing books");
				System.out.println(
						"--------------------------------------------------------------------------------------------------------------------------------------");

				for (Book book : bi.getBookList()) {
					System.out.printf("%-5d| %-20s\n", book.getCode(), book.getTitle());
				}
				System.out.println(
						"*************************************************************************************************************************************");

			} else {
				k++;
			}
			if (borrowingInfoList.size() == k) {
				System.out.println("There is no Borrowing Infomation");
			}
		}
	}

	static void displayBorrowerByDepartmentName(String name) {
		int k = 0;
		System.out.println(
				"======================================================================================================================================");
		System.out.printf("%-5s| %-20s| %-15s| %-11s| %s\n", "CODE", "FULL NAME", "ID", "BIRTH YEAR", "DEPARTMENT");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");

		for (Borrower b : borrowerList) {
			if (b.getDepartment().getName().toLowerCase().contains(name.toLowerCase())) {
				System.out.println(b.toString());
			} else
				k++;
		}
		if (k == borrowerList.size()) {
			System.out.println("No borrower belong to this department.");
		}
	}

	static void displayBorrowerByDepartmentShortName(String sn) {
		int k = 0;
		System.out.println(
				"======================================================================================================================================");
		System.out.printf("%-5s| %-20s| %-15s| %-11s| %s\n", "CODE", "FULL NAME", "ID", "BIRTH YEAR", "DEPARTMENT");
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------------------------------");

		for (Borrower b : borrowerList) {

			if (b.getDepartment().getShortName().toLowerCase().contains(sn.toLowerCase())) {
				System.out.println(b.toString());
			} else
				k++;
		}
		if (k == borrowerList.size()) {
			System.out.println("No borrower belong to this department.");
		}
	}

	static void displayBorrowerBelongToDepartment() {
		String name;
		boolean found = false;
		sc.nextLine();
		while (!found) {
			System.out.println("Type in Department Name or ShortName: ");
			name = sc.nextLine();
			if (isDepartmentName(name)) {
				displayBorrowerByDepartmentName(name);
				found = true;
			} else if (isDepartmentShortName(name)) {
				displayBorrowerByDepartmentShortName(name);
				found = true;
			} else {
				System.err.println("This Department is not exists.");
				found = false;
			}
		}

	}

	static void displayAllDepartment() {
		System.out.println(
				"============================================================================================");
		System.out.printf("%-5s| %-10s\t| %-20s\n", "CODE", "SHORT NAME", "NAME");
		System.out.println("----------------------------------------------------------------");
		for (Department d : departmentList) {
			System.out.println(d.toString());
		}
	}

	// ***************END DISPLAY
	// METHODS************************************************************************************************/

	// ***************ADD
	// METHODS************************************************************************************************/

	static void addBook() {
		boolean added;
		System.out.printf("Quantity: ");
		int i = sc.nextInt();
		for (int j = 0; j < i; j++) {
			added = false;
			sc.nextLine();
			while (!added) {
				int code = bookList.size();
				int currentyear = Calendar.getInstance().get(Calendar.YEAR);
				boolean stringErr = true;
				while (stringErr) {
					System.out.printf("Type in Book's bin code: ");
					String binCode = sc.nextLine();
					System.out.printf("Type in Book's title: ");
					String title = sc.nextLine();
					System.out.printf("Type in Book's republish: ");
					String republish = sc.nextLine();
					if (!title.isEmpty() && !republish.isEmpty() && !binCode.isEmpty()) {
						boolean yearErr = true;
						while (yearErr) {
							try {
								System.out.printf("Type in Book's publish year: ");
								int year = sc.nextInt();
								if (year > currentyear) {
									System.err.println("Year can't be greater than current year.");
									sc.nextLine();
								} else if (year <= 0) {
									System.err.println("Invalid year.");
									sc.nextLine();
								} else {
									boolean authorErr = true;
									while (authorErr) {
										try {
											System.out.printf("Type in Book's author in the author list: ");
											int author = sc.nextInt();
											if (author < 0) {
												System.err.println("Invalid code.");
												sc.nextLine();
											} else if (author >= authorList.size()) {
												System.err.println("This author is not exists");
												sc.nextLine();

											} else {
												bookList.add(new Book(code, title, republish, year, binCode,
														authorList.get(author)));
												rw.writeToFile(BOOK_FILE_NAME, bookList);
												System.out.println("New Book has been added successfully.");
												authorErr = false;
												yearErr = false;
												stringErr = false;
												added = true;
											}
										} catch (InputMismatchException e) {
											System.err.println("Type in number only.");
											sc.nextLine();
										}
									}
								}
							} catch (InputMismatchException e) {
								System.err.println("Type in year only.");
								sc.nextLine();
							}
						}
					} else {
						System.err.println("None of Infomation can be null.");
					}
				}

			}
		}
	}

	static void addAuthor() {
		boolean added;
		System.out.printf("Quantity: ");
		int i = sc.nextInt();

		for (int j = 0; j < i; j++) {
			added = false;
			sc.nextLine();
			while (!added) {
				try {
					int code = authorList.size();
					int currentYear = Calendar.getInstance().get(Calendar.YEAR);
					boolean stringErr = true;
					while (stringErr) {
						System.out.printf("Type in Author's Name: ");
						String name = sc.nextLine();
						System.out.printf("Type in Author's Introduce: ");
						String introduce = sc.nextLine();
						if (!name.isEmpty() && !introduce.isEmpty()) {
							boolean yearErr = true;
							while (yearErr) {
								try {
									System.out.printf("Type in Author's Year of birth: ");
									int birthYear = sc.nextInt();
									if (birthYear <= 0) {
										System.err.println("Invalid year.");
										sc.nextLine();
									} else {
										System.out.printf(
												"Type in Author's Year of death (type in 0 if the author is not death): ");
										int deathYear = sc.nextInt();
										if (birthYear > deathYear && deathYear != 0 && birthYear > 0) {
											System.err.println("Year of birth can't be greater than year of death.");
											sc.nextLine();
										} else if (birthYear > currentYear || deathYear > currentYear) {
											System.err.println("Year can't be greater than current year.");
											sc.nextLine();
										} else if (birthYear <= 0 || deathYear < 0 || (deathYear - birthYear) > 120) {
											System.err.println("Invalid year.");
											sc.nextLine();
										} else {
											authorList.add(new Author(code, name, birthYear, deathYear, introduce));
											rw.writeToFile(AUTHOR_FILE_NAME, authorList);
											System.out.println("New Author has been added successfully.");
											yearErr = false;
											stringErr = false;
											added = true;
										}
									}

								} catch (InputMismatchException e) {
									System.err.println("Type in year only, please retype.");
									sc.nextLine();
								}
							}
						} else {
							System.err.println("None of Infomation can be null.");
						}
					}

				} catch (InputMismatchException e) {
					System.err.println("Type in year only, please redo everything.");
					sc.nextLine();
				}
			}
		}
	}

	static void addBorrower() {
		int department;
		System.out.printf("Quantity: ");
		int i = sc.nextInt();
		sc.nextLine();
		for (int j = 0; j < i; j++) {
			boolean added = false;
			boolean found = false;
			while (!added) {
				try {
					int code = borrowerList.size();
					int currentYear = Calendar.getInstance().get(Calendar.YEAR);
					System.out.printf("Type in Borrower's Name: ");
					String name = sc.nextLine();
					if (!name.isEmpty()) {
						boolean idErr = true;
						while (idErr) {
							try {
								System.out.printf("Type in Borrower's Id: ");
								int id = sc.nextInt();
								if (id < 0) {
									System.err.println("Invalid Id.");
									sc.nextLine();
								} else {
									boolean yearErr = true;
									while (yearErr) {
										try {
											System.out.printf("Type in Borrower's Year of birth: ");
											int birthYear = sc.nextInt();
											sc.nextLine();
											if (birthYear > currentYear) {
												System.err.println("Year can't be greater than current year.");
											} else if (birthYear < (currentYear - 120)) {
												System.err.println("This person should be dead.");
											} else {
												while (!found) {
													System.out.println("Type in Department Name or ShortName: ");
													String dName = sc.nextLine();
													if (isDepartmentName(dName)) {
														department = getDepartmentCodeByName(dName);
														borrowerList.add(new Borrower(code, name, id, birthYear,
																departmentList.get(department)));
														System.out.println("New Borrower has been added successfully.");
														found = true;
														yearErr = false;
														idErr = false;
														added = true;
													} else if (isDepartmentShortName(dName)) {
														department = getDepartmentCodeByShortName(dName);
														borrowerList.add(new Borrower(code, name, id, birthYear,
																departmentList.get(department)));
														rw.writeToFile(BORROWER_FILE_NAME, borrowerList);
														System.out.println("New Borrower has been added successfully.");
														found = true;
														yearErr = false;
														idErr = false;
														added = true;

													} else {
														System.err.println("This Department is not exists.");
													}
												}
											}
										} catch (InputMismatchException e) {
											System.err.println("Type in year only.");
											sc.nextLine();
										}

									}

								}
							} catch (InputMismatchException e) {
								System.err.println("Type in number only.");
								sc.nextLine();
							}
						}

					} else {
						System.err.println("None of Infomation can be null.");
						sc.nextLine();
					}
				} catch (InputMismatchException e) {
					System.err.println("Type in year only, please redo everything.");
					sc.nextLine();
				}
			}
		}
	}

	static void addDepartment() {
		boolean added;
		System.out.printf("Quantity: ");
		int i = sc.nextInt();
		sc.nextLine();
		for (int j = 0; j < i; j++) {
			added = false;
			int code = departmentList.size();
			while (!added) {
				System.out.printf("Type in Department's Name: ");
				String dName = sc.nextLine();
				System.out.printf("Type in Department's Short Name: ");
				String dSName = sc.nextLine();
				if (!dName.isEmpty() && !dSName.isEmpty()) {
					departmentList.add(new Department(code, dName, dSName));
					rw.writeToFile(DEPARTMENT_FILE_NAME, departmentList);
					System.out.println("New Department has been added successfully");
					added = true;
				} else {
					System.err.println("Department's Name and Department's Short Name can't be null.");
				}
			}
		}
	}

	static void addBorrowingInfo() {
		boolean added;
		System.out.printf("Quantity: ");
		int i = sc.nextInt();
		sc.nextLine();
		for (int j = 0; j < i; j++) {
			added = false;
			while (!added) {
				try {
					System.out.println("Type in Code of Borrower: ");
					int code = sc.nextInt();
					if (code < 0) {
						System.err.println("Invalid code.");
					} else if (code >= borrowerList.size()) {
						System.err.println("This borrower is not exists.");
						boolean addednew = false;
						sc.nextLine();
						while (!addednew) {
							System.out.println("Do you want to create a new borrower (Y/N)? ");
							String yn = sc.nextLine();
							if (yn.toLowerCase().equals("y")) {
								boolean err = true;
								while (err) {
									try {
										addBorrower();
										code = borrowerList.size();
										addednew = true;
										err = false;
									} catch (InputMismatchException e) {
										System.err.println("Type in number only.");
										sc.next();
									}
								}
							} else if (yn.toLowerCase().equals("n")) {
								addednew = true;
								break;
							} else {
								addednew = false;
							}
						}
					} else {
						boolean timeErr = true;
						while (timeErr) {
							try {
								System.out.println("Type in Borrow Time: ");
								int bT = sc.nextInt();
								if (bT > 0) {
									boolean bookErr = true;
									while (bookErr) {
										try {
											System.out.println("Type in Code of Book: ");
											int codeOfBook = sc.nextInt();
											if (codeOfBook < 0) {
												System.err.println("Invalid code.");
											} else if ((codeOfBook) < bookList.size()) {
												borrowingInfoList
														.add(new BorrowingInfo(code, bookList.get(codeOfBook), bT));
												bookList.get(codeOfBook).setStatus(STATUS.Borrowed);
												rw.writeToFile(BORROWINGINFO_FILE_NAME, borrowingInfoList);
												rw.writeToFile(BOOK_FILE_NAME, bookList);
												System.out.println(
														"New Borrowing Information has been added successfully.");
												timeErr = false;
												bookErr = false;
												added = true;
											} else {
												System.err.println("This Book is not exists.");
											}
										} catch (InputMismatchException e) {
											System.err.println("Type in number only.");
											sc.next();
										}
									}
								} else {
									System.err.println("Invalid borrow time.");
								}
							} catch (InputMismatchException e) {
								System.err.println("Type in number only.");
								sc.next();
							}
						}

					}
				} catch (InputMismatchException e) {
					System.err.println("Type in number only.");
					sc.next();
				}
			}
		}
	}

	// ***************END ADD
	// METHODS************************************************************************************************/
	// ***************UPDATE
	// METHODS************************************************************************************************/
	static void updateDepartmentName() {
		displayAllDepartment();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Department's code: ");
				int code = sc.nextInt();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < departmentList.size()) {
					System.out.println("Department's Full Name: " + departmentList.get(code).getName());
					sc.nextLine();
					while (!updated) {
						System.out.printf("Type in Department new Name: ");
						String name = sc.nextLine();
						if (!name.isEmpty()) {
							departmentList.get(code).setName(name);
							rw.writeToFile(DEPARTMENT_FILE_NAME, departmentList);
							System.out.println("Department Name's has been updated successfully.");
							displayAllDepartment();
							updated = true;
						} else {
							System.err.println("Name can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Department is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateDepartmentShortName() {
		displayAllDepartment();
		System.out.println(
				"********************************************************************************************");
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Department's code: ");
				int code = sc.nextInt();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < departmentList.size()) {
					System.out.println("Department's Short Name: " + departmentList.get(code).getShortName());
					sc.nextLine();
					while (!updated) {
						System.out.printf("Type in Department new Short Name: ");
						String name = sc.nextLine();
						if (!name.isEmpty()) {
							departmentList.get(code).setShortName(name);
							rw.writeToFile(DEPARTMENT_FILE_NAME, departmentList);
							System.out.println("Department's Short Name has been updated successfully.");
							displayAllDepartment();
							updated = true;
						} else {
							System.err.println("Short Name can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Department is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateBookTitle() {
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Code of Book: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < bookList.size()) {
					System.out.println("Book's Title: " + bookList.get(code).getTitle());
					while (!updated) {
						System.out.printf("Type in Book's new Title: ");
						String title = sc.nextLine();
						if (!title.isEmpty()) {
							bookList.get(code).setTitle(title);
							rw.writeToFile(BOOK_FILE_NAME, bookList);
							System.out.println("Book's Title has been updated successfully.");
							updated = true;
						} else {
							System.err.println("Title can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Book is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateBookAuthor() {
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Code of Book: ");
				int code = sc.nextInt();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < bookList.size()) {
					System.out.println("Book's Author: ");
					for (Author a : bookList.get(code).getAuthorList()) {
						System.out.println(a.getCode() + "-" + a.getFullName());
					}
					while (!updated) {
						try {
							System.out.printf("Type in Book's Author's Code you want to change: ");
							int authorCode = sc.nextInt();
							if (authorCode < 0) {
								System.err.println("Invalid code.");
							} else if (authorCode < authorList.size()) {
								for (Author a : bookList.get(code).getAuthorList()) {
									if (authorCode == a.getCode()) {
										boolean aErr = true;
										while (aErr) {
											try {
												System.out.printf("Type in new Author's Code: ");
												int newACode = sc.nextInt();
												if (newACode < 0) {
													System.err.println("Invalid code.");
												} else if (newACode < authorList.size()) {
													bookList.get(code).getAuthorList().remove(authorCode);
													bookList.get(code).getAuthorList().add(authorList.get(newACode));
													rw.writeToFile(BOOK_FILE_NAME, bookList);
													System.out.println("Book's Author has been updated successfully.");
													aErr = false;
													updated = true;
												} else {
													System.err.println("This author is not exists.");
												}
											} catch (InputMismatchException ie) {
												System.err.println("Type in Integer number only");
												sc.next();
											}
										}
										break;
									} else {
										System.err.println("This author is not this book's author");
									}
								}
							} else {
								System.err.println("This author is not exists.");
							}

						} catch (InputMismatchException ie) {
							System.err.println("Type in Integer number only");
							sc.next();
						}
					}
					err = false;
				} else {
					System.err.println("This Book is not exists.");
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
			}
		}
	}

	static void updateBookRepublish() {
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Code of Book: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < bookList.size()) {
					System.out.println("Book's Republish: " + bookList.get(code).getRepublish());
					while (!updated) {
						System.out.printf("Type in Book's new republish: ");
						String republish = sc.nextLine();
						if (!republish.isEmpty()) {
							bookList.get(code).setRepublish(republish);
							rw.writeToFile(BOOK_FILE_NAME, bookList);
							System.out.println("Book's republish has been updated successfully.");
							updated = true;
						} else {
							System.err.println("Republish can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Book is not exists.");
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
			}
		}
	}

	static void updateBookPublishYear() {
		boolean updated = false;
		boolean err = true;
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		while (err) {
			try {
				System.out.printf("Type in Code of Book: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < bookList.size()) {
					System.out.println("Book's publish year: " + bookList.get(code).getPublishYear());
					while (!updated) {
						try {
							System.out.printf("Type in Book's new publish year: ");
							int pYear = sc.nextInt();
							if (pYear <= 0) {
								System.err.println("Invalid year.");
							} else if (pYear <= currentYear) {
								bookList.get(code).setPublishYear(pYear);
								rw.writeToFile(BOOK_FILE_NAME, bookList);
								System.out.println("Book's publish year has been updated successfully.");
								updated = true;
							} else {
								System.err.println("Publish year can't be greater than current year.");
							}
						} catch (InputMismatchException ie) {
							System.err.println("Type in valid year.");
							sc.next();
						}
					}
					err = false;
				} else {
					System.err.println("This Book is not exists.");
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
			}
		}
	}

	static void updateBookBinCode() {
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Code of Book: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < bookList.size()) {
					System.out.println("Book's Republish: " + bookList.get(code).getBinCode());
					while (!updated) {
						System.out.printf("Type in Book's new republish: ");
						String binCode = sc.nextLine();
						if (!binCode.isEmpty()) {
							bookList.get(code).setBinCode(binCode);
							rw.writeToFile(BOOK_FILE_NAME, bookList);
							System.out.println("Book's bin code has been updated successfully.");
							updated = true;
						} else {
							System.err.println("Bin code can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This Book is not exists.");
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
			}
		}
	}

	static void updateAuthorName() {
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Code of Author: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < authorList.size()) {
					System.out.println("Author's Name: " + authorList.get(code).getFullName());
					while (!updated) {
						System.out.printf("Type in Author's new Name: ");
						String name = sc.nextLine();
						if (!name.isEmpty()) {
							authorList.get(code).setFullName(name);
							rw.writeToFile(AUTHOR_FILE_NAME, authorList);
							System.out.println("Author's Name has been updated successfully.");
							updated = true;
						} else {
							System.err.println("Name can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This author is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateAuthorBirthYear() {
		boolean updated = false;
		boolean err = true;
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		while (err) {
			try {
				System.out.printf("Type in Code of Author: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < authorList.size()) {
					System.out.println("Author's year of birth: " + authorList.get(code).getYearOfBirth());
					while (!updated) {
						try {
							System.out.printf("Type in Author's new year of birth: ");
							int bYear = sc.nextInt();
							if (bYear <= 0) {
								System.err.println("Invalid year.");
							} else if (bYear <= currentYear) {
								authorList.get(code).setYearOfBirth(bYear);
								rw.writeToFile(AUTHOR_FILE_NAME, authorList);
								System.out.println("Author's year of birth has been updated successfully.");
								updated = true;
							} else {
								System.err.println("birth year can't be greater than current year.");
							}
						} catch (InputMismatchException ie) {
							System.err.println("Type in valid year.");
							sc.next();
						}
					}
					err = false;
				} else {
					System.err.println("This Author is not exists.");
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
			}
		}
	}

	static void updateAuthorDeathYear() {
		boolean updated = false;
		boolean err = true;
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		while (err) {
			try {
				System.out.printf("Type in Code of Author: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < authorList.size()) {
					System.out.println("Author's year of death: " + authorList.get(code).getYearOfDeath());
					while (!updated) {
						try {
							System.out.printf("Type in Author's new year of death: ");
							int dYear = sc.nextInt();
							if (dYear < 0) {
								System.err.println("Invalid year.");
							} else if (dYear < authorList.get(code).getYearOfBirth()) {
								System.err.println("Year of Death must be greater than year of birth.");
							} else if (dYear <= currentYear) {
								authorList.get(code).setYearOfBirth(dYear);
								rw.writeToFile(AUTHOR_FILE_NAME, authorList);
								System.out.println("Author's year of birth has been updated successfully.");
								updated = true;
							} else {
								System.err.println("birth year can't be greater than current year.");
							}
						} catch (InputMismatchException ie) {
							System.err.println("Type in valid year.");
							sc.next();
						}
					}
					err = false;
				} else {
					System.err.println("This Author is not exists.");
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
			}
		}
	}

	static void updateAuthorIntroduce() {
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Code of Author: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < authorList.size()) {
					System.out.println("Author's Introducing: " + authorList.get(code).getIntroduce());
					while (!updated) {
						System.out.printf("Type in Author's new Introducing: ");
						String intro = sc.nextLine();
						if (!intro.isEmpty()) {
							authorList.get(code).setIntroduce(intro);
							rw.writeToFile(AUTHOR_FILE_NAME, authorList);
							System.out.println("Author's Introducing has been updated successfully.");
							updated = true;
						} else {
							System.err.println("Introducing can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This author is not exists.");
					err = true;
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
				err = true;
			}
		}
	}

	static void updateBorrowerName() {
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Code of Borrower: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < borrowerList.size()) {
					System.out.println("Borrower's Name: " + borrowerList.get(code).getFullName());
					while (!updated) {
						System.out.printf("Type in Borrower's new Name: ");
						String name = sc.nextLine();
						if (!name.isEmpty()) {
							borrowerList.get(code).setFullName(name);
							rw.writeToFile(BORROWER_FILE_NAME, borrowerList);
							System.out.println("Borrower's Name has been updated successfully.");
							updated = true;
						} else {
							System.err.println("Name can't be null.");
						}
					}
					err = false;
				} else {
					System.err.println("This borrower is not exists.");
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
			}
		}
	}

	static void updateBorrowerId() {
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Code of Borrower: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < borrowerList.size()) {
					System.out.println("Borrower's Id number: " + borrowerList.get(code).getId());
					while (!updated) {
						try {
							System.out.printf("Type in Borrower's new Id: ");
							int id = sc.nextInt();
							if (id > 0) {
								borrowerList.get(code).setId(id);
								rw.writeToFile(BORROWER_FILE_NAME, borrowerList);
								System.out.println("Borrower's Id has been updated successfully.");
								updated = true;
							} else {
								System.err.println("Invalid Id number.");
								sc.nextLine();
							}
						} catch (InputMismatchException ie) {
							System.err.println("Type in Integer number only");
							sc.nextLine();
						}
					}
					err = false;
				} else {
					System.err.println("This borrower is not exists.");
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.nextLine();
			}
		}
	}

	static void updateBorrowerBirthYear() {
		boolean updated = false;
		boolean err = true;
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		while (err) {
			try {
				System.out.printf("Type in Code of Author: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < borrowerList.size()) {
					System.out.println("Borrower's year of birth: " + borrowerList.get(code).getYearOfBirth());
					while (!updated) {
						try {
							System.out.printf("Type in Borrower's new year of birth: ");
							int bYear = sc.nextInt();
							if (bYear < (currentYear - 120)) {
								System.out.println("This person should be dead.");
							} else if (bYear < currentYear) {
								borrowerList.get(code).setYearOfBirth(bYear);
								rw.writeToFile(BORROWER_FILE_NAME, borrowerList);
								System.out.println("Borrower's year of birth has been updated successfully.");
								updated = true;
							} else {
								System.err.println("birth year can't be greater than current year.");
							}
						} catch (InputMismatchException ie) {
							System.err.println("Type in valid year.");
							sc.nextLine();
						}
					}
					err = false;
				} else {
					System.err.println("This Author is not exists.");
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.nextLine();
			}
		}
	}

	static void updateBorrowerDepartment() {
		boolean updated = false;
		boolean err = true;
		while (err) {
			try {
				System.out.printf("Type in Code of Borrower: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < borrowerList.size()) {
					System.out.println("Borrower's Department: " + borrowerList.get(code).getDepartment().getName());
					while (!updated) {
						try {
							System.out.printf("Type in Borrower's new Department's Code: ");
							int dCode = sc.nextInt();
							if (dCode < 0) {
								System.err.println("Invalid code.");
							} else if (dCode < departmentList.size()) {
								borrowerList.get(code).setDepartment(departmentList.get(dCode));
								rw.writeToFile(BORROWER_FILE_NAME, borrowerList);
								System.out.println("Borrower's Department has been updated successfully.");
								updated = true;
							} else {
								System.err.println("This Department is not exists.");
							}
						} catch (InputMismatchException ie) {
							System.err.println("Type in Integer number only");
							sc.nextLine();
						}
					}
					err = false;
				} else {
					System.err.println("This Borrower is not exists.");
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
			}
		}
	}

	static void updateBorrowingInfoActualRT() {
		boolean updated = false;
		boolean err = true;
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		while (err) {
			try {
				System.out.printf("Type in Code of Borrowing Infomation: ");
				int code = sc.nextInt();
				sc.nextLine();
				if (code < 0) {
					System.err.println("Invalid code.");
				} else if (code < borrowingInfoList.size()) {
					System.out.println("Borrowering Info: " + borrowingInfoList.get(code).toString());
					while (!updated) {
						boolean yearErr = true;
						while (yearErr) {
							try {
								System.out.printf("Type in actual return time's year: ");
								int year = sc.nextInt();
								sc.nextLine();
								if (year > currentYear) {
									System.err.println("Invalid year.");
								} else {
									boolean monthErr = true;
									while (monthErr) {
										try {
											System.out.printf("Type in actual return time's month: ");
											int month = sc.nextInt();
											sc.nextLine();
											if (month <= 0 || month > 12) {
												System.err.println("Invalid month.");
											} else {
												boolean dayErr = true;
												while (dayErr) {
													try {
														System.out.printf("Type in actual return time's day: ");
														int day = sc.nextInt();
														sc.nextLine();
														if (day <= 0 || day > 31) {
															System.err.println("Invalid day.");
														} else {
															if (isValidDate(day, month, year)) {

																try {
																	String dateString = day + "/" + month + "/" + year;
																	Date date = new SimpleDateFormat("dd/MM/yyyy")
																			.parse(dateString);
																	if (date.compareTo(borrowingInfoList.get(code)
																			.getbTime()) < 0) {
																		System.err.println(
																				"Return date must be after borrowed date.");
																		dayErr = false;
																		monthErr = false;
																	} else {
																		borrowingInfoList.get(code).setActualRT(date);
																		rw.writeToFile(BORROWINGINFO_FILE_NAME,
																				borrowingInfoList);
																		System.out.println(
																				"Borrowing infomation's Actual return time has been updated successfully.");
																		dayErr = false;
																		monthErr = false;
																		yearErr = false;
																		updated = true;
																	}
																} catch (ParseException e) {
																	e.printStackTrace();
																	sc.next();
																}
															} else {
																System.err.println("Invalid date: " + year
																		+ " is a not leap year and "
																		+ getMonthString(month)
																		+ " only have 28 days.");
																dayErr = false;
																monthErr = false;
															}
														}
													} catch (InputMismatchException ie) {
														System.err.println("Type in Integer number only");
														sc.next();
													}
												}
											}
										} catch (InputMismatchException ie) {
											System.err.println("Type in Integer number only");
											sc.next();
										}
									}
								}
							} catch (InputMismatchException ie) {
								System.err.println("Type in Integer number only");
								sc.next();
							}
						}
					}
					err = false;
				} else {
					System.err.println("This borrowering info is not exists.");
				}
			} catch (InputMismatchException ie) {
				System.err.println("Type in Integer number only");
				sc.next();
			}
		}
	}

	// ***************END UPDATE
	// METHODS************************************************************************************************/

	// ***************MENU
	// METHODS************************************************************************************************/
	static void printDisplayMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("DISPLAY MENU");
		System.out.println("1. All of book.");
		System.out.println("2. All of book, which status is available.");
		System.out.println("3. All of author.");
		System.out.println("4. All of author, who still live.");
		System.out.println("5. All of Borrower.");
		System.out.println("6. All of borrower belong to any department.");
		System.out.println("7. All of borrowing information.");
		System.out.println("8. All of borrowing information, which borrower still not return book yet.");
		System.out.println("9. All of borrowing information, which borrower return book not on time.");
		System.out.println("10. Return to Main menu.");
		System.out.println("0. Quit.");
	}

	static void printAddingMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("ADDING MENU");
		System.out.println("1. Add new Book.");
		System.out.println("2. Add new Author.");
		System.out.println("3. Add new Borrower.");
		System.out.println("4. Add new Department.");
		System.out.println("5. Add new Borrowing Infomation.");
		System.out.println("6. Return to Main Menu.");
		System.out.println("0. Quit.");
	}

	static void printUpdateMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("UPDATE MENU");
		System.out.println("1. Update Book.");
		System.out.println("2. Update Author.");
		System.out.println("3. Update Borrower.");
		System.out.println("4. Update Department.");
		System.out.println("5. Update Borrowing Infomation.");
		System.out.println("6. Return to Main Menu.");
		System.out.println("0. Quit.");
	}

	static void printUpdateBookMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("UPDATE BOOK MENU");
		System.out.println("1. Title.");
		System.out.println("2. Athor.");
		System.out.println("3. Republish.");
		System.out.println("4. Publish year.");
		System.out.println("5. Bin code.");
		System.out.println("6. Return to Main Menu.");
		System.out.println("0. Quit.");
	}

	static void printUpdateAuthorMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("UPDATE AUTHOR MENU");
		System.out.println("1. Full name.");
		System.out.println("2. Year of birth.");
		System.out.println("3. Year of death.");
		System.out.println("4. Introducing about author.");
		System.out.println("5. Return to Main Menu.");
		System.out.println("0. Quit.");
	}

	static void printUpdateBorrowerMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("UPDATE BORROWER MENU");
		System.out.println("1. Full name.");
		System.out.println("2. Id number.");
		System.out.println("3. Year of birth.");
		System.out.println("4. Department.");
		System.out.println("5. Return to Main Menu.");
		System.out.println("0. Quit.");
	}

	static void printUpdateDepartmentMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("UPDATE DEPARTMENT MENU");
		System.out.println("1. Name.");
		System.out.println("2. Short name.");
		System.out.println("3. Return to Update Menu.");
		System.out.println("0. Quit.");
	}

	static void printUpdateBorrowingInfoMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("UPDATE BORROWING INFOMATION MENU");
		System.out.println("1. Actual return time.");
		System.out.println("2. Return to Update Menu.");
		System.out.println("0. Quit.");
	}

	static void printMainMenu() {
		System.out.println(
				"============================================================================================");
		System.out.println("MAIN MENU");
		System.out.println("1. Display Information Menu.");
		System.out.println("2. Adding Menu.");
		System.out.println("3. Update Information Menu.");
		System.out.println("0. Quit.");
	}

	static void updateBookMenu() {
		int option;
		do {
			printUpdateBookMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				updateBookTitle();
				break;
			case 2:
				updateBookAuthor();
				break;
			case 3:
				updateBookRepublish();
				break;
			case 4:
				updateBookPublishYear();
				break;
			case 5:
				updateBookBinCode();
				break;
			case 6:
				break;

			default:
				System.out.println("Wrong input, please retype.");
				break;
			}

		} while (option != 6);
	}

	static void updateAuthorMenu() {
		int option;
		do {
			printUpdateAuthorMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				updateAuthorName();
				break;
			case 2:
				updateAuthorBirthYear();
				break;
			case 3:
				updateAuthorDeathYear();
				break;
			case 4:
				updateAuthorIntroduce();
				break;
			case 5:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}
		} while (option != 5);
	}

	static void updateBorrowerMenu() {
		int option;
		do {
			printUpdateBorrowerMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				updateBorrowerName();
				break;
			case 2:
				updateBorrowerId();
				break;
			case 3:
				updateBorrowerBirthYear();
				break;
			case 4:
				updateBorrowerDepartment();
				break;
			case 5:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}

		} while (option != 5);
	}

	static void updateDepartmentMenu() {
		int option;
		do {
			printUpdateDepartmentMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				updateDepartmentName();
				break;
			case 2:
				updateDepartmentShortName();
				break;
			case 3:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}
		} while (option != 3);

	}

	static void updateBorrowingInfoMenu() {
		int option;
		do {
			printUpdateBorrowingInfoMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				updateBorrowingInfoActualRT();
				break;
			case 2:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}
		} while (option != 2);

	}

	static void UpdateMenu() throws InputMismatchException {
		int option;
		boolean error;
		do {
			printUpdateMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				error = true;
				do {
					try {
						updateBookMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 2:
				error = true;
				do {
					try {
						updateAuthorMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 3:
				error = true;
				do {
					try {
						updateBorrowerMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 4:
				error = true;
				do {
					try {
						updateDepartmentMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 5:
				error = true;
				do {
					try {
						updateBorrowingInfoMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 6:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}
		} while (option != 6);
	}

	static void displayMenu() throws InputMismatchException {
		int option;
		do {
			printDisplayMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				displayAllBook();
				break;
			case 2:
				displayAllAvailableBook();
				break;
			case 3:
				displayAllAuthor();
				break;
			case 4:
				displayAliveAuthor();
				break;
			case 5:
				displayAllBorrower();
				break;
			case 6:
				displayBorrowerBelongToDepartment();
				break;
			case 7:
				displayAllBorrowingInfo();
				break;
			case 8:
				displayBorrowingInfoWhichIsNotReturned();
				break;
			case 9:
				displayBorrowingInfoWhichIsLateReturned();
				break;
			case 10:
				break;

			default:
				System.out.println("Wrong input, please retype.");
				break;
			}

		} while (option != 10);
	}

	static void addingMenu() throws InputMismatchException {
		int option;
		boolean error;
		do {
			printAddingMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				error = true;
				do {
					try {

						addBook();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("\nType in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;

			case 2:
				error = true;
				do {
					try {
						addAuthor();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("\nType in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 3:
				error = true;
				do {
					try {
						addBorrower();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("\nType in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 4:
				error = true;
				do {
					try {
						addDepartment();

						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("\nType in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 5:
				error = true;
				do {
					try {
						addBorrowingInfo();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("\nType in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 6:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}
		} while (option != 6);
	}

	static void mainMenu() throws InputMismatchException {
		int option;
		boolean error;
		do {
			printMainMenu();
			System.out.printf("Type in Number in the Menu: ");
			option = sc.nextInt();
			switch (option) {
			case 0:
				System.exit(0);
				break;
			case 1:
				error = true;
				do {
					try {
						displayMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 2:
				error = true;
				do {
					try {
						addingMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 3:
				error = true;
				do {
					try {
						UpdateMenu();
						error = false;
					} catch (InputMismatchException ie) {
						System.err.println("Type in number only");
						error = true;
						sc.next();
					}
				} while (error);
				break;
			case 4:
				break;
			default:
				System.out.println("Wrong input, please retype.");
				break;
			}
		} while (option != 4);
	}

	// ***************END MENU
	// METHODS************************************************************************************************/
	public static void main(String[] args) {
		dumpData();
		boolean error;
		do {
			try {
				mainMenu();
				error = false;
			} catch (InputMismatchException ie) {
				System.err.println("Type in number only");
				error = true;
				sc.next();
			}
		} while (error);

	}

}
