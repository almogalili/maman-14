package Q2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class PhoneBook {

	private TreeMap<String, String> phoneBook;

	private static Scanner input;

	private static Path filePath;
	private static final int FIRST_NAME_POSITION = 0;
	private static final int LAST_NAME_POSTION = 1;
	private static final int PHONE_NUMBER_POSITION = 2;
	private static final int LIMIT_SPLIT = 0;

	public PhoneBook(Path filePath) {

		this.phoneBook = new TreeMap<String, String>();
		PhoneBook.filePath = filePath;
		openFile();
		readRecords();
		closeFile();

	}

	public TreeMap<String, String> getPhoneBook() {
		return phoneBook;
	}

	private void openFile() {

		try {

			input = new Scanner(filePath);

		} catch (IOException e) {

			System.err.println("Error opening file");
			System.exit(1);

		}

	}

	private void readRecords() {

		try {

			while (input.hasNext()) {

				/*
				 * I tried to do that with split. if i swap the positions in the text file, it
				 * works fine with LIMIT_SPLIT = 2; but i do not want to swap, How can i do that
				 * with split?
				 * 
				 */

				String[] record = input.nextLine().split(" ", LIMIT_SPLIT);

				String fullName = new StringBuilder(record[FIRST_NAME_POSITION]).append(" ")
						.append(record[LAST_NAME_POSTION]).toString();
				
				phoneBook.put(fullName, record[PHONE_NUMBER_POSITION]);

			}

		} catch (NoSuchElementException elementException) {

			System.err.println("File improperly formed, Terminating");

		} catch (IllegalStateException stateException) {

			System.err.println("Error reading from file, Terminating");

		}

	}

	private void closeFile() {
		if (input != null) {
			input.close();
		}
	}
	

}
