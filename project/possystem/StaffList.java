package possystem;

import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A list to store all staffs' information.
 * 
 * @author xiongying
 * @version 1.0
 * @since 2021-11-09
 */
public class StaffList {

	/**
	 * Constant Value of File Path Read Data of Staff From File Name: Staffs.csv
	 * Column 1: int id Column 2: String name Column 3: String gender Column 4:
	 * String jobTitle Column 5: boolean isWorking
	 */
	public static final String STAFF_CSV_FILE_PATH = new String(
			"/Users/xiongying/Desktop/Assignment2/possystem/Staffs.csv");

	/**
	 * List of all staffs info
	 */
	static List<Staff> staffList = new ArrayList<Staff>();

	/**
	 * Create a class constructor for the Staff List class
	 */
	public StaffList() {
		// Convert from data to object
		parseStaffCSVtoObject();
	}

	/**
	 * Read staff item from .csv file, convert line to Staff object, save to array
	 */
	public static void parseStaffCSVtoObject() {
		// Parsing csv file into Scanner class constructor
		String line = "";
		String splitBy = ",";

		try {
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader reader = new BufferedReader(new FileReader(STAFF_CSV_FILE_PATH));
			while ((line = reader.readLine()) != null) {
				// Convert line to String Array
				String[] item = line.replace("\"", "").split(splitBy);
				// Convert String array to Staff object & add to Staff List
				Staff staff = new Staff(Integer.parseInt(item[0]), item[1].replace("|", ","), item[2].replace("|", ","),
						item[3].replace("|", ","), item[4].equals("1") ? true : false);
				staffList.add(staff);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print all Staff Info in Staff List
	 */
	public void printStaffList() {

		// Print Header
		System.out.println();
		System.out.println("___________________________STAFF LIST___________________________");
		System.out.println();

		// Print Items
		for (Staff staff : staffList) {
			staff.printDetail();
		}
		System.out.println();
		// Print Footer
		System.out.println("______________________________________________________________");

	}

	/**
	 * Pad string with spaces on right
	 * 
	 * @param s
	 * @param n
	 * @return formatted string align to the right
	 */
	public static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);
	}

	/**
	 * Pad string with spaces on left
	 * 
	 * @param s
	 * @param n
	 * @return formatted string align to the left
	 */
	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);
	}
}
