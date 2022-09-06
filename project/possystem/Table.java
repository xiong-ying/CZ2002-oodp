package possystem;

/**
 * The class represent a table in the restaurant.
 * 
 * @version 1.0
 * @since 2021-11-12
 * @author Chong Hui Yee
 *
 */
public class Table {

	/**
	 * The unique table number in the restaurant.
	 */
	int tableNo;
	/**
	 * The number of seats of the table.
	 */
	int seats;

	/**
	 * Create a class constructor for the Table class.
	 * 
	 * @param tableNo This Table's unique number.
	 * @param seats   This Table's seats.
	 */
	public Table(int tableNo, int seats) {
		this.tableNo = tableNo;
		this.seats = seats;
	}

	/**
	 * @return the tableNo
	 */
	public int getTableNo() {
		return tableNo;
	}

	/**
	 * @param tableNo the tableNo to set
	 */
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}

	/**
	 * @return the seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * @param seats the seats to set
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
	 * Print table
	 */
	public void printTable() {
		System.out.println();
		System.out.printf(padLeft("Table " + String.valueOf(tableNo), 10));
		System.out.printf(padLeft(String.valueOf(seats) + " seats", 50));
		System.out.println();
		System.out.println();
	}

	/**
	 * Pad string with spaces on right
	 * 
	 * @param s The string that need padding.
	 * @param n The length of the padding on the right.
	 * @return paddedString
	 */
	public static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);
	}

	/**
	 * Pad string with spaces on left
	 * 
	 * @param s The string that need padding.
	 * @param n The length of the padding on the left.
	 * 
	 * @return paddedString
	 */
	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);
	}
}
