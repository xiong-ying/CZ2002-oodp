package possystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class handles all tables in the restaurant. View table availability based
 * on date and time.
 * 
 * @version 1.0
 * @since 2021-11-12
 * @author Chong Hui Yee
 *
 */
public class TableManager {
	/**
	 * The constant values for Tables.cvs file path.
	 */
	public static final String Table_CSV_FILE_PATH = new String(
			"/Users/xiongying/Desktop/Assignment2/possystem/Tables.csv");

	/**
	 * The list of tables in the restaurant.
	 */
	static List<Table> tableList = new ArrayList<Table>();

	/**
	 * Create a class constructor for the TableManager class Initialisation include
	 * fetching item from csv to variable.
	 */
	public TableManager() {
		parseTableCSVtoObject();
	}

	/**
	 * Read tables from .csv file and convert line to Table object and save to
	 * array.
	 */
	public static void parseTableCSVtoObject() {
		// Parsing a Tables.csv file into Scanner class constructor
		String line = "";
		String splitBy = ",";

		try {
			tableList.clear();
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader reader = new BufferedReader(new FileReader(Table_CSV_FILE_PATH));
			while ((line = reader.readLine()) != null) {
				// Convert line to String Array
				String[] item = line.replace("\"", "").split(splitBy);
				// Convert String array to Table object & add to tableList
				Table tbl = new Table(Integer.parseInt(item[0]), Integer.parseInt(item[1]));
				tableList.add(tbl);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the tableList of the restaurant
	 * 
	 * @return the tableList
	 */
	public static List<Table> getTableList() {
		return tableList;
	}

	/**
	 * Check table availability based on future date
	 * Mainly use for reservation
	 * 
	 * @param dateTime the selected date
	 * @return the isTableAvailable
	 */
	public static List<Table> getAvailableTableList(LocalDateTime dateTime) {

		ReservationManager rManager = new ReservationManager();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-YYYY");
		String formattedDate = dateTime.format(myFormatObj);

		// Filter reservation on that day
		List<Reservation> filterReservationList = rManager.getReservationList().stream()
				.filter(p -> (p.getDateString().equals(formattedDate))).collect(Collectors.toList());

		// Available Table list
		List<Table> availableTableList = new ArrayList<Table>();
		availableTableList.addAll(tableList);

		for (Reservation item : filterReservationList) {
			// Check if looking for lunch or dinner session
			if (dateTime.getHour() >= 18 && !item.isLunchReservation()) {
				// Filter out taken tables
				availableTableList
						.removeIf(tbl -> (item.getStatus() == Status.Reserved )
								&& tbl.getTableNo() == item.getTableNumber());
			} else if (dateTime.getHour() < 18 && item.isLunchReservation()) {
				// Filter out taken tables
				availableTableList
						.removeIf(tbl -> (item.getStatus() == Status.Reserved )
								&& tbl.getTableNo() == item.getTableNumber());
			}
		}

		return availableTableList;
	}

	/**
	 * Check table availability based on now for walk in customer.
	 * 
	 * Table that are reserve will only be available on reserve time.
	 * If reserved table has not made order after 15mins at reserved time, table will be open for availability.
	 * 
	 * @return the table available now
	 */
	public List<Table> getAvailableTableNowForWalkIn() {

		ReservationManager rManager = new ReservationManager();
		OrderList orderList = new OrderList();
		LocalDateTime dateTime = LocalDateTime.now();

		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-YYYY");
		String formattedDate = dateTime.format(myFormatObj);

		// Filter reservation on current day
		List<Reservation> filterReservationList = rManager.getReservationList().stream()
				.filter(p -> (p.getDateString().equals(formattedDate))).collect(Collectors.toList());

		// Available Table list
		List<Table> availableTableList = new ArrayList<Table>();
		availableTableList.addAll(tableList);

		for (Reservation item : filterReservationList) {
			// Check if looking for lunch or dinner session
			if (dateTime.getHour() >= 18 && !item.isLunchReservation()) {
				// Filter out reserved tables
				availableTableList.removeIf(tbl -> (item.getStatus() == Status.Reserved
						&& item.getDateTime().isBefore(dateTime.plusMinutes(15))));
			} else if (dateTime.getHour() < 18 && item.isLunchReservation()) {
				// Filter out reserved tables
				availableTableList.removeIf(tbl -> (item.getStatus() == Status.Reserved
						&& item.getDateTime().isBefore(dateTime.plusMinutes(15))));
			}
		}

		for (Order item : orderList.getOrderList()) {
			// Remove tables that are occupied at the moment
			if (!item.getIsPaid()) {
				availableTableList.removeIf(tbl -> item.getTableNum() == tbl.getTableNo());
			}
		}

		return availableTableList;
	}

	/**
	 * Print table availability based on now for walk in customers
	 * 
	 * Table that are reserve will only be available on reserve time. If reserved
	 * table has not made order after 15mins at reserved time, table will be open
	 * for availability.
	 * 
	 */
	public void printAvailableTableNowForWalkIn() {

		List<Table> availableTableList = getAvailableTableNowForWalkIn();
		
		if (availableTableList.size() == 0) {
			
			System.out.println();
			System.out.println("No available tables at the moment...");
			System.out.println();
			return;
		}

		// Print Menu Header
		System.out.println();
		System.out.println("__________________________Open Tables___________________________");
		System.out.println();

		for (Table tbl : availableTableList) {
			tbl.printTable();
		}
		// Print Menu Footer
		System.out.println("________________________________________________________________");

	}
	
}
