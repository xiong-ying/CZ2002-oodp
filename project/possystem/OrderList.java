

package possystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
A list to store all the orders.
@author 	xiongying
@version 	1.0
@since		2021-11-09
*/
public class OrderList {

	/**
	 * Constant Value of File Path
	 * Read Data of Orders From File Name: Orders.csv
	 * Column 1: int id
	 * Column 2: String timestamp
	 * Column 3: int serverID
	 * Column 4: int tableNum
	 * Column 5: int pax
	 * Column 6: boolean isMember
	 * Column 7: boolean isPaid
	 * Column 8: List MenuItem menuList, List PromoItem promoList
	 * */
	public static final String ORDER_CSV_FILE_PATH = new String(
			"/Users/xiongying/Desktop/Assignment2/possystem/Orders.csv");
	
	/**
	 * List of all the order items
	 */
	static List<Order> orderList = new ArrayList<Order>();

	/**
	 * Create a class constructor for the Menu class, convert orders from data to object
	 */
	public OrderList() {
		parseOrderCSVtoObject();
	}


	/**
	 * Read Order item from csv file, convert line to Order object, save to array
	 */
	public void parseOrderCSVtoObject() {
		// Parsing a Order.csv file into Scanner class constructor
		String line = "";
		String splitBy = ",";

		Menu menu = new Menu();
		
		try {
			orderList.clear();
			// parsing a CSV file into BufferedReader class constructor
			BufferedReader reader = new BufferedReader(new FileReader(ORDER_CSV_FILE_PATH));
			while ((line = reader.readLine()) != null) {
				// Convert line to String Array
				String[] item = line.replace("\"", "").split(splitBy);

				// Get MenuItem array from string
				List<MenuItem> mList = new ArrayList<MenuItem>();
				String[] mStringList = item[7].split("\\|");
				// Search in menuList for matching id and add into mList
				for (MenuItem menuItem : menu.getMenuList()) {
					for (String menuString : mStringList) {
						if (!menuString.equals("") && menuItem.id == Integer.parseInt(menuString)) {
							mList.add(menuItem);
						}
					}
				}

				// Get PromoItem array from string
				List<PromoItem> pList = new ArrayList<PromoItem>();
				String[] pStringList = item[7].split("\\|");
				// Search in promoList for matching id and add into pList
				for (PromoItem promoItem : menu.getPromoList()) {
					for (String pString : pStringList) {
						if (!pString.equals("") && promoItem.id == Integer.parseInt(pString)) {
							pList.add(promoItem);
						}
					}
				}

				// Convert String array to Order object
				Order order = new Order(Integer.parseInt(item[0]), item[1].replace("|", ","), Integer.parseInt(item[2]),
						Integer.parseInt(item[3]), Integer.parseInt(item[4]), item[5].equals("1") ? true : false,
						item[6].equals("1") ? true : false);
				order.setMenuList(mList);
				order.setPromoList(pList);

				// Add Order object to OrderList
				orderList.add(order);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print all active orders in order list
	 */
	public void printActiveOrder() {

		// Print Order Header
		System.out.println();
		System.out.println("_____________________________ACTIVE ORDER LIST_____________________________");
		System.out.println();

		// Print Order List
		for (Order item : orderList) {
			// Print order item only if it is active (unpaid)
			if (!item.getIsPaid()) {
				item.viewOrder();
			}
		}

		// Print Order Footer
		System.out.println("____________________________________________________________________________");
	}

	/**
	 * Print all orders, including past order (paid and unpaid)
	 */
	public void printAllOrder() {

		// Print Order Header
		System.out.println();
		System.out.println("______________________________FULL ORDER LIST_____________________________");
		System.out.println();

		// Print Order List
		for (Order item :orderList) {
			item.viewOrder();
		}

		// Print Order Footer
		System.out.println("____________________________________________________________________________");
	}
	
	/**
	 * Add a new Order into orderList and save in csv
	 * @throws IOException
	 */
	public void addNewOrder() throws IOException {
		
		// Auto generate order id and timeStamp
		int id = orderList.size()+1; //0
		String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		// Temporary Variables
		int serverID; //2
		int tableNum = -1; //3
		Table selectedTable = null;
		boolean isMember = false; //5
		boolean isPaid = false; //6
		List<MenuItem> menuList = new ArrayList<MenuItem>(); //7
		List<PromoItem> promoList = new ArrayList<PromoItem>(); //7
		boolean isCompleted = false;
		
		//Reservation
		ReservationManager rManager = new ReservationManager();
		Reservation reserveTable = null;
		
		// Get all inputs from user
		Scanner newScanner = new Scanner(System.in);
		System.out.println();
		System.out.println("Adding a new order... ");
		
		System.out.println("Please enter your staff ID: ");
		serverID = newScanner.nextInt();
		
		System.out.println("Customer have membership? (Y/N): ");
		String member = newScanner.next();
		
		if (member.equals("Y")) { isMember = true;}
		
		//Assigning table
		System.out.println("Customer have reservation? (Y/N): ");
		String haveReservation = newScanner.next();
		
		if (haveReservation.equals("Y")) { 
			
			// Print and get reservation for the day
			List<Reservation> rftdList = rManager.printAndGetAllReservationForTheDay();
			
			//Check if there is any reservation
			if (rftdList.size() == 0) {
				System.out.println();
				System.out.println("No reservation for the day, please try again...");
				System.out.println();
				return;
			}
			
			//Check if user enter valid table number
			while(tableNum == -1) {
				System.out.println("Please enter the table number: ");
				tableNum = newScanner.nextInt();
				
				for (Reservation r : rftdList) {
					if (r.getTableNumber() == tableNum){
						//Valid table number
						selectedTable = new Table(r.getTableNumber(), r.getPax());
						reserveTable = r;
						
					}
				}
				
				//If unable to find table 
				if (selectedTable == null) {
					System.out.println();
					System.out.println("Invalid table number enter, please try again...");
					System.out.println();
					tableNum = -1;
				}
				
			}
			
			
		}else {
			TableManager tblManager = new TableManager();
			/* Check table availability, print out available table with capacity*/
			List<Table> tblList = tblManager.getAvailableTableNowForWalkIn();
			tblManager.printAvailableTableNowForWalkIn();
			
			//Check if there is any table
			if (tblList.size() == 0) {
				System.out.println();
				System.out.println("No table available, please try again...");
				System.out.println();
				return;
			}
			
			//Check if user enter valid table number
			while(tableNum == -1) {
				System.out.println("Please enter the table number: ");
				tableNum = newScanner.nextInt();
				
				for (Table tbl : tblList) {
					if (tbl.getTableNo() == tableNum){
						//Valid table number
						selectedTable = tbl;
						
					}
				}
				
				//If unable to find table 
				if (selectedTable == null) {
					System.out.println();
					System.out.println("Invalid table number enter, please try again...");
					System.out.println();
					tableNum = -1;
				}
				
			}
		}

		System.out.println();
		
		//Print existing menu to user
		Menu menu = new Menu();
		menu.printAllMenuItems();
		menu.printAllPromoItems();
		
		System.out.println();
		System.out.println("Add menu item or promo item to order... ");
		
		// Ask user input menu items id until user is satisfied
		while (!isCompleted) {
			Boolean didFind = false;
			// Request for item id
			System.out.println("Please enter item's id no: ");
			int itemID = newScanner.nextInt();
			
			// Check if itemID is valid in menuList
			for (MenuItem menuItem : Menu.menuList) {
				// If menu item is valid, add to menuList
				if (menuItem.id == itemID && menuItem.isAvailable) {
					//Update didFind variable, this show that itemID is valid
					didFind = true;
					menuList.add(menuItem);
					System.out.println(menuItem.name + " is added to Order " + id);
				}
			}
			
			//Check if item ID is valid in PromoList
			for (PromoItem promoItem : Menu.promoList) {
				// If promo item is valid, add to promoList
				if (promoItem.id == itemID && promoItem.isAvailable) {
					//Update didFind variable, this show that itemID is valid
					didFind = true;
					promoList.add(promoItem);
					System.out.println(promoItem.name + " is added to Order " + id);
				}
			}
			
			if (!didFind) {
				//if itemID cannot be found, request user to try again 
				System.out.println("** Invalid item's id no, please try again... ");
			}else {
				Boolean isValidConfirmation = false;

				while (!isValidConfirmation) {
					//else if itemID was found, request user if need to add another item 
					System.out.println("Do you want to add another item? (Y/N)");
					Scanner conScanner = new Scanner(System.in);
					String confirmation = conScanner.nextLine();
					if (confirmation.equals("N")) {
						// Completed adding of menu items
						isValidConfirmation = true;
						isCompleted = true;
					}else if (confirmation.equals("Y")) {
						isValidConfirmation = true;
					}else {
						System.out.println("** Invalid input please try again... ");
					}
				}
			}
		}
		
		// Change "," to "|" for save in Csv
		// Add new order item to orderList
		Order order = new Order(orderList.size()+1, timestamp.replace(",", "|"), serverID, tableNum, selectedTable.getSeats(), isMember, isPaid);
		order.setMenuList(menuList);
		order.setPromoList(promoList);
		
		orderList.add(order);
		//orderListForInvoice.add(order);
		
		// Save updated order list to csv
		saveOrderToCSV();
		
		//Check for any update in reservations
		if (reserveTable != null) {
			reserveTable.setStatus(Status.Seated);
			rManager.saveAndUpdateAllReservation();
		}
		
		// Show user the new added promotion item
		System.out.println();
		System.out.println("******************************************************************");
		//printActiveOrder();
		System.out.println("Order sucessfully added!");
		System.out.println("******************************************************************");

	}
	
	
	/**
	 * @return the orderList
	 */
	public List<Order> getOrderList() {
		return orderList;
	}


	/**
	 * Add Item to existing Order and save in csv
	 * @throws IOException
	 */
	public void addItemToOrder() throws IOException {
		// Temporary Variables
		int orderId;
		Boolean didFind = false;
		
		Boolean isCompleted = false;
		Order updatedItem = null;

		// Print order items for user selection
		printActiveOrder();

		Scanner updateScanner = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);

		while (!didFind) {
			
			// Get order id input from user
			System.out.println();
			System.out.println("Please enter the order no. to be edited:");
			orderId = updateScanner.nextInt();
			// Filter out the order item based on user input
			for (Order order : orderList) {
				if (order.getID() == orderId && !(order.getIsPaid())) {
					// Display the order to be edited
					System.out.println();
					System.out.println("Order to be edited...");
					order.viewOrder();
					
					// Print existing menu to user
					Menu menu = new Menu();
					menu.printAllMenuItems();
					menu.printAllPromoItems();

					System.out.println("Add item to order...");
					
					// Ask user input items id until user is satisfied
					while (!isCompleted) {
						// Request for item id
						System.out.println("Please enter item's id no: ");
						int itemID = scanner.nextInt();
						
						// Check if itemID is valid in menuList
					
						
						for (MenuItem menuItem : Menu.menuList) {
							// If menu item is valid, add to menuList
							if (menuItem.id == itemID && menuItem.isAvailable) {
								//Update didFind variable, this show that itemID is valid
								didFind = true;
								order.getMenuList().add(menuItem);
								System.out.println(menuItem.name + " is added to Order " + order.getID());
							}
						}
						
						// Check if itemID is valid in promoList
						for (PromoItem promoItem : Menu.promoList) {
							// If menu item is valid, add to promoList
							if (promoItem.id == itemID && promoItem.isAvailable) {
								//Update didFind variable, this show that itemID is valid
								didFind = true;
								order.getPromoList().add(promoItem);
								System.out.println(promoItem.name + " is added to Order " + order.getID());
							}
						}
						
						if (!didFind) {
							//if itemID cannot be found, request user to try again 
							System.out.println("** Invalid item's id no, please try again... ");
						}else {
							Boolean isValidConfirmation = false;
							
							while (!isValidConfirmation) {
								//else if itemID was found, request user if need to add another item 
								System.out.println("Do you want to add another item? (Y/N)");
								Scanner conScanner = new Scanner(System.in);
								String confirmation = conScanner.nextLine();
								if (confirmation.equals("N")) {
									// Completed adding of items
									isValidConfirmation = true;
									isCompleted = true;
								} else if (confirmation.equals("Y")) {
									isValidConfirmation = true;
								} else {
									System.out.println("** Invalid input please try again... ");
								}
							}
						}
					}

					// Update variable indicating that item did exist
					didFind = true;
					updatedItem = order;
					break;
				}
			}
			// If unable to find item in order, ask user to key in valid order id
			if (!didFind) {
				System.out.println();
				System.out.println("Invalid order ID, please try again...");
				updateScanner = new Scanner(System.in);
			}

		}

		// Save new order list to csv
		//saveOrderToCSV();

		// Show user the new added order item
		System.out.println();
		System.out.println("******************************************************************");
		if (updatedItem != null)
			updatedItem.viewOrder();
		System.out.println("Order sucessfully updated!");
		System.out.println("******************************************************************");
	}

	/**
	 * Remove Item from active Order and save in csv
	 * @throws IOException
	 */
	// -----------------------------------------------------------------
	public void removeItemFromOrder() throws IOException {
		// Temporary Variables
		int orderId;
		Boolean didFind = false;
		
		Boolean isCompleted = false;
		Order updatedItem = null;
		
		List<MenuItem> mList = new ArrayList<MenuItem>(); 
		List<PromoItem> pList = new ArrayList<PromoItem>();

		// Print order items for user selection
		printActiveOrder();

		Scanner updateScanner = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);

		while (!didFind) {
			
			// Get order id input from user
			System.out.println();
			System.out.println("Please enter the order no. to be edited:");
			orderId = updateScanner.nextInt();
			// Filter out the order item based on user input
			for (Order order : orderList) {
				if (order.getID() == orderId && !(order.getIsPaid())) {
					// Display the order to be edited
					System.out.println();
					System.out.println("Order to be edited...");
					order.viewOrder();

					// Ask user which item to remove
					System.out.println("Remove item from order...");
					// Ask user input items id until user is satisfied
					while (!isCompleted) {
						// Request for item id
						System.out.println("Please enter item's id no: ");
						int itemID = scanner.nextInt();
						
						// Check if itemID is valid in menuList
						for (MenuItem menuItem : order.getMenuList()) {
							// If menu item is valid, add to mList
							if (menuItem.id == itemID && menuItem.isAvailable) {
								//Update didFind variable, this show that itemID is valid
								didFind = true;
								
								mList.add(menuItem);
								
								System.out.println(menuItem.name + " is removed from Order " + order.getID());
							}
						}
						
						// remove items from menuList
						for (MenuItem menuItem : mList) {
							order.getMenuList().remove(menuItem);
						}
						
						// Check if itemID is valid in promoList
						for (PromoItem promoItem : order.getPromoList()) {
							// If menu item is valid, add to pList
							if (promoItem.id == itemID && promoItem.isAvailable) {
								//Update didFind variable, this show that itemID is valid
								didFind = true;
								pList.add(promoItem);
								System.out.println(promoItem.name + " is removed from Order " + order.getID());
							}
						}
						
						// remove items from promoList
						for (PromoItem promoItem : pList) {
							order.getPromoList().remove(promoItem);
						}
						
						
						if (!didFind) {
							//if itemID cannot be found, request user to try again 
							System.out.println("** Invalid item's id no, please try again... ");
						}else {
							Boolean isValidConfirmation = false;
							
							while (!isValidConfirmation) {
								//else if itemID was found, request user if need to add another item 
								System.out.println("Do you want to remove another item? (Y/N)");
								Scanner conScanner = new Scanner(System.in);
								String confirmation = conScanner.nextLine();
								if (confirmation.equals("N")) {
									// Completed removing of items
									isValidConfirmation = true;
									isCompleted = true;
								} else if (confirmation.equals("Y")) {
									isValidConfirmation = true;
								} else {
									System.out.println("** Invalid input please try again... ");
								}
							}
						}
					}

					// Update variable indicating that item did exist
					didFind = true;
					updatedItem = order;
					break;
				}
			}
			// If unable to find item in order, ask user to key in valid order id
			if (!didFind) {
				System.out.println();
				System.out.println("Invalid order ID, please try again...");
				updateScanner = new Scanner(System.in);
			}

		}

		// Save new order list to csv
		saveOrderToCSV();

		// Show user the deleted item
		System.out.println();
		System.out.println("******************************************************************");
		if (updatedItem != null)
			updatedItem.viewOrder();
		System.out.println("Order sucessfully updated!");
		System.out.println("******************************************************************");
	}

	/**
	 * Save the Order List to CSV file
	 * @throws IOException
	 */
	public void saveOrderToCSV() throws IOException {
		List<String> olist = new ArrayList<>();

		for (Order orderItem : orderList) {
		olist.add(orderItem.convertObjectToCsvString());
		}
				
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ORDER_CSV_FILE_PATH))) {
			for (String line : olist) {
				bw.write(line);
				bw.newLine();
			}
		}
	}

	
	
}
