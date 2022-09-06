package possystem;

import java.util.List;
import java.util.Scanner;

/**
 * An order is created to record all the dishes that a customer ordered. When
 * the order invoice is printed, the order is paid, the order status is
 * inactive.
 * 
 * @author xiongying
 * @version 1.0
 * @since 2021-11-08
 */
public class Order {

	/**
	 * Order ID
	 */
	private int id;

	/**
	 * TimeStamp when the order's created
	 */
	private String timestamp;

	/**
	 * ID of the staff who created this order
	 */
	private int serverID;

	/**
	 * Table assigned to this order
	 */
	private int tableNum;

	/**
	 * The number of customers for this order
	 */
	private int pax;

	/**
	 * The membership status of the customer
	 */
	private boolean isMember;

	/**
	 * The payment status of this order
	 */
	private boolean isPaid;

	/**
	 * A list of ordered items from Menu
	 */
	private List<MenuItem> menuList;

	/**
	 * A list of ordered items from Promotion
	 */
	private List<PromoItem> promoList;

	public Invoice invoiceObj = new Invoice();

	public Invoice getInvoiceObj() {
		return invoiceObj;
	}

	public void setInvoiceObj(Invoice invoiceObj) {
		this.invoiceObj = invoiceObj;
	}

	/**
	 * Scanner
	 */
	Scanner sc = new Scanner(System.in);

	/**
	 * Create a class constructor for the order class
	 * 
	 * @param id
	 * @param timestamp
	 * @param serverID
	 * @param tableNum
	 * @param pax
	 * @param isMember
	 * @param isPaid
	 */
	// -----------------------------------------------------------------
	public Order(int id, String timestamp, int serverID, int tableNum, int pax, boolean isMember, boolean isPaid) {
		this.id = id;
		this.timestamp = timestamp;
		this.serverID = serverID;
		this.tableNum = tableNum;
		this.pax = pax;
		this.isMember = isMember;
		this.isPaid = isPaid;

	}

	/**
	 * Take order item and convert object to csv string format
	 * 
	 * @return csv string format of order item
	 */
	public String convertObjectToCsvString() {

		// String[] record = {String.valueOf(id) ,timestamp, String.valueOf(serverID),
		// String.valueOf(tableNum),String.valueOf(pax), isMember?"1":"0",
		// isPaid?"1":"0"};
		String result = new String(
				String.valueOf(id) + "," + timestamp + "," + String.valueOf(serverID) + "," + String.valueOf(tableNum)
						+ "," + String.valueOf(pax) + "," + (isMember ? "1" : "0") + "," + (isPaid ? "1" : "0") + ",");

		for (MenuItem mItem : menuList) {
			result = result + mItem.id + "|";
		}
		for (PromoItem pItem : promoList) {
			result = result + pItem.id + "|";
		}

		return result;
	}

	/**
	 * Print out the details of this order
	 */
	public void viewOrder() {
		// view the order
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		System.out.printf("Order :%03d", id);
		System.out.printf("\n");
		System.out.println();
		System.out.println("Time: " + timestamp);

		// System.out.printf("Server: %03d", serverID);
		// System.out.printf("\n");
		System.out.println("Server Name: " + getServerName(serverID));

		System.out.println("Table Number: " + tableNum);
		System.out.println("No. of customer: " + pax);

		String member = isMember ? "Member" : "Non-Member";
		System.out.println("Membership: " + member);

		String payment = isPaid ? "Paid" : "Unpaid";
		System.out.println("Payment: " + payment);

		System.out.println();

		for (MenuItem mItem : menuList) {
			System.out.printf("     ");
			System.out.printf("* ");
			System.out.printf("%03d. ", mItem.id);
			System.out.printf(padRight(mItem.name.replace("|", ","), 20));
			System.out.println();
		}

		for (PromoItem pItem : promoList) {
			System.out.printf("     ");
			System.out.printf("* ");
			System.out.printf("%03d. ", pItem.id);
			System.out.printf(padRight(pItem.name.replace("|", ","), 20));
			System.out.println();
		}

		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println();

	}

	/**
	 * Get menuList
	 * 
	 * @return menuList
	 */
	// -----------------------------------------------------------------
	public List<MenuItem> getMenuList() {
		return menuList;
	}

	/**
	 * set menuList
	 * 
	 * @param items
	 */
	public void setMenuList(List<MenuItem> items) {
		this.menuList = items;
	}

	/**
	 * get promoList
	 * 
	 * @return promoList
	 */
	public List<PromoItem> getPromoList() {
		return promoList;
	}

	/**
	 * set promoList
	 * 
	 * @param items
	 */
	public void setPromoList(List<PromoItem> items) {
		this.promoList = items;
	}

	/**
	 * get the ID of this order
	 * 
	 * @return id
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * get the server's staff ID of this order
	 * 
	 * @return serverID
	 */
	public int getServerID() {
		return this.serverID;
	}

	/**
	 * Change the server for this order
	 * 
	 * @param serverID
	 */
	public void setServerID(int serverID) {
		this.serverID = serverID;
	}

	/**
	 * Get the server's name by his/her staff ID
	 * 
	 * @param serverID
	 * @return the full name of the staff
	 */
	public String getServerName(int serverID) {

		for (Staff staff : StaffList.staffList) {
			// If menu item is valid, add to mList
			if (staff.getId() == serverID) {
				// Update didFind variable, this show that itemID is valid
				return staff.getName();
			}
		}
		return null;
	}

	/**
	 * Get table number for this order
	 * 
	 * @return tableNum
	 */
	public int getTableNum() {
		return this.tableNum;
	}

	/**
	 * Set table number for this order
	 * 
	 * @param tableNum
	 */
	public void setTableNum(int tableNum) {
		this.tableNum = tableNum;
	}

	/**
	 * get number of customers
	 * 
	 * @return number of customers
	 */
	public int getPax() {
		return this.pax;
	}

	/**
	 * set number of customers
	 * 
	 * @param pax
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}

	/**
	 * get customer's membership status
	 * 
	 * @return isMember
	 */
	public Boolean getIsMember() {
		return this.isMember;
	}

	/**
	 * set customer's membership status
	 * 
	 * @param isMember
	 */
	public void setIsMember(Boolean isMember) {
		this.isMember = isMember;
	}

	/**
	 * get the order payment status
	 * 
	 * @return isPaid
	 */
	public Boolean getIsPaid() {
		return this.isPaid;
	}

	/**
	 * Set the order payment status
	 * 
	 * @param isPaid
	 */
	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	/**
	 * Pad string with spaces on right
	 * 
	 * @param s
	 * @param n
	 * @return a string that align to right
	 */
	public static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);
	}

	/**
	 * Pad string with spaces on Left
	 * 
	 * @param s
	 * @param n
	 * @return a string that align to left
	 */
	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);
	}
}
